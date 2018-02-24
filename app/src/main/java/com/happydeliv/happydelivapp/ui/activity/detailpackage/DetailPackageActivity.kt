package com.happydeliv.happydelivapp.ui.activity.detailpackage

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.bumptech.glide.Glide
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.gson.Gson
import com.happydeliv.happydelivapp.R
import com.happydeliv.happydelivapp.utils.FirebaseDB
import com.scoproject.newsapp.ui.common.BaseActivity
import com.tedpark.tedpermission.rx2.TedRx2Permission
import kotlinx.android.synthetic.main.activity_detail_package.*
import kotlinx.android.synthetic.main.activity_toolbar.*
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.uiThread
import java.net.URL
import javax.inject.Inject

/**
 * Created by ibnumuzzakkir on 07/02/18.
 * Android Engineer
 * SCO Project
 */
class DetailPackageActivity : BaseActivity(), DetailPackageContract.View, OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {
    @Inject
    lateinit var mDetailPackagePresenter : DetailPackagePresenter

    @Inject
    lateinit var mFirebaseDB : FirebaseDB


    var mGoogleApiClient: GoogleApiClient? = null
    var mLocationRequest: LocationRequest? = null
    var mMap: GoogleMap? = null
    var mapFrag: SupportMapFragment? = null
    var latLng : LatLng? = null
    var currLocationMarker : Marker? = null
    lateinit var mDriverCurrLati :String
    lateinit var mDriverCurrLongi :String
    var mDestinationLat : String? = null
    var mDestinationLong : String? = null
    var mTrackId : String? = null

    override fun onActivityReady(savedInstanceState: Bundle?) {
        mDetailPackagePresenter.attachView(this)
        setupUIListener()
        TedRx2Permission.with(this)
                .setPermissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE)
                .request()
                .subscribe({ tedPermissionResult ->
                    if (tedPermissionResult.isGranted) {
                        //Permission Granted
                        mapFrag = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
                        mapFrag?.getMapAsync(this)
                    } else {
                        //Denied by user
                    }
                }, { throwable -> throwable.message}, { })
        val bundle = intent.extras
        if (bundle != null) {
            mTrackId = bundle.getString("data")
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_detail_package
    }

    override fun setupUIListener() {
        imgbtn_back.visibility = View.VISIBLE
        imgbtn_back.setOnClickListener {
           onBackPressed()
        }
    }

    @SuppressLint("MissingPermission")
    override fun setupContent(expeditionName: String,
                              trackingId: String,
                              imageUrl: String,
                              driverName: String,
                              phoneDriver: String) {
        tv_company_name.text = expeditionName
        tv_courier_name.text = driverName
        tv_detail_track_id.text = "Track id : " + trackingId
        Glide.with(this)
                .load(imageUrl)
                .into(iv_courier_photo)
        btn_phone_driver.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phoneDriver))
            startActivity(callIntent)
        }

        //SMS Action
        btn_message_driver.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", phoneDriver, null)))
        }

    }

    override fun onBackPressed() {
        mActivityNavigation.navigateToHomePage()
        super.onBackPressed()
    }

    override fun showLoading() {
        pb_detail_package.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_detail_package.visibility = View.GONE
    }

    override fun showError(content: String) {
        Toast.makeText(this, content, Toast.LENGTH_LONG).show()
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap
        mMap!!.mapType = GoogleMap.MAP_TYPE_TERRAIN
        mMap!!.isMyLocationEnabled = true
        setupGoogleClient()

        mDetailPackagePresenter.getPackageDetail(mTrackId!!)
        mFirebaseDB.gettingCourierLastLocation(mTrackId!!, object : FirebaseDB.GetFireBaseCallBack{
            override fun onSuccess(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEach {
                    Log.d(javaClass.name,dataSnapshot.toString())
                    mDriverCurrLongi = it.child("driverCurrentLong").value.toString()
                    mDriverCurrLati = it.child("driverCurrentLat").value.toString()
                    mDestinationLat = it.child("destinationCurrentLat").value.toString()
                    mDestinationLong = it.child("destinationCurrentLong").value.toString()
                    Log.d(javaClass.name, mDriverCurrLongi + "," + mDriverCurrLati)

                    if(mGoogleApiClient != null){
                        mMap?.clear()
                        latLng = LatLng(mDriverCurrLati.toDouble(), mDriverCurrLongi.toDouble())
                        addMarker(mDriverCurrLati.toDouble(),mDriverCurrLongi.toDouble(), R.drawable.ic_marker_kurir, "Lokasi Courier")
                        addMarker(mDestinationLat?.toDouble()!!,mDestinationLong?.toDouble()!!, R.drawable.ic_marker_kurir_tujuan, "Lokasi Tujuan")
                        //zoom to current position:
                        mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15F))
                        drawDirection(mDriverCurrLati.toDouble(),mDriverCurrLongi.toDouble(),mDestinationLat?.toDouble(),mDestinationLong?.toDouble())
                    }

                }
            }

            override fun onError(databaseError: DatabaseError) {
                Log.d(javaClass.name, databaseError.message.toString())
            }

        })
    }

    private fun setupGoogleClient(){
        mGoogleApiClient = GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build()

        if (mGoogleApiClient != null) {
            mGoogleApiClient?.connect()
        } else {
//            Toast.makeText(this, "Not Connected!", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("MissingPermission")
    override fun onConnected(p0: Bundle?) {

    }

    override fun onConnectionSuspended(p0: Int) {
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
    }

    override fun onLocationChanged(location: Location?) {
    }

    override fun drawDirection(currentLat: Double?, currentLong: Double?, destinationLat: Double?, destinationLong: Double?) {
        // declare bounds object to fit whole route in screen
        val LatLongB = LatLngBounds.Builder()

        // Add markers
        val driver = LatLng(currentLat!!, currentLong!!)
        val destination = LatLng(destinationLat!!,destinationLong!!)

        // Declare polyline object and set up color and width
        val options = PolylineOptions()
        options.color(R.color.colorDarkOrange)
        options.width(8f)

        // build URL to call API
        val url = getURL(driver, destination)

        async {
            // Connect to URL, download content and convert into string asynchronously
            val result = URL(url).readText()
            uiThread {
                // When API call is done, create parser and convert into JsonObjec
                val parser = Parser()
                val stringBuilder = StringBuilder(result)
                val json: JsonObject = parser.parse(stringBuilder) as JsonObject
                // get to the correct element in JsonObject
                val routes = json.array<JsonObject>("routes")
                if(routes?.size!! > 0){
                    val points = routes["legs"]["steps"][0] as JsonArray<JsonObject>
                    // For every element in the JsonArray, decode the polyline string and pass all points to a List
                    val polypts = points.flatMap { decodePoly(it.obj("polyline")?.string("points")!!)  }
                    // Add  points to polyline and bounds
                    options.add(driver)
                    LatLongB.include(driver)
                    for (point in polypts)  {
                        options.add(point)
                        LatLongB.include(point)
                    }
                    options.add(destination)
                    LatLongB.include(destination)
                    // build bounds
                    val bounds = LatLongB.build()
                    // add polyline to the map
                    mMap?.addPolyline(options)
                    // show map with route centered
                    mMap?.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100))

                    val legs  = routes!!["legs"][0] as JsonArray<JsonObject>
                    val distanceArr = legs[0]["distance"] as JsonObject
                    val distance = distanceArr["text"]
                    val durationArr = legs[0]["duration"] as JsonObject
                    val duration = durationArr["text"]

                    setContentDurationAndDistance(duration.toString(), distance.toString())
                }
            }
        }
    }

    override fun addMarker(lati: Double, longi: Double, marker: Int, titleMarker: String) {
        latLng = LatLng(lati, longi)
        val markerOptions = MarkerOptions()
        markerOptions.position(latLng!!)
        markerOptions.title(titleMarker)
        //Setup Marker Driver
        val bitmap = BitmapFactory.decodeResource(this.resources,marker)
        val markerDriver = BitmapDescriptorFactory.fromBitmap(bitmap)
        markerOptions.icon(markerDriver)

        mMap!!.addMarker(markerOptions)
    }

    override fun setContentDurationAndDistance(duration: String, distance: String) {
        tv_duration.visibility = View.VISIBLE
        tv_distance.visibility = View.VISIBLE

        tv_distance?.text = "Jarak : " + distance
        tv_duration?.text = "Waktu tempuh : " + duration
    }



    override fun onDestroy() {
        mFirebaseDB.removeListener()
        super.onDestroy()
    }


    private fun getURL(from : LatLng, to : LatLng) : String {
        val origin = "origin=" + from.latitude + "," + from.longitude
        val dest = "destination=" + to.latitude + "," + to.longitude
        val sensor = "sensor=false"
        val params = "$origin&$dest&$sensor"
        return "https://maps.googleapis.com/maps/api/directions/json?$params"
    }

    /**
     * Method to decode polyline points
     * Courtesy : https://jeffreysambells.com/2010/05/27/decoding-polylines-from-google-maps-direction-api-with-java
     */
    private fun decodePoly(encoded: String): List<LatLng> {
        val poly = ArrayList<LatLng>()
        var index = 0
        val len = encoded.length
        var lat = 0
        var lng = 0

        while (index < len) {
            var b: Int
            var shift = 0
            var result = 0
            do {
                b = encoded[index++].toInt() - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lat += dlat

            shift = 0
            result = 0
            do {
                b = encoded[index++].toInt() - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lng += dlng

            val p = LatLng(lat.toDouble() / 1E5,
                    lng.toDouble() / 1E5)
            poly.add(p)
        }

        return poly
    }


}