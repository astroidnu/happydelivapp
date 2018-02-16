package com.happydeliv.happydelivapp.ui.activity.detailpackage

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.gson.Gson
import com.happydeliv.happydelivapp.R
import com.happydeliv.happydelivapp.utils.FirebaseDB
import com.scoproject.newsapp.ui.common.BaseActivity
import com.tedpark.tedpermission.rx2.TedRx2Permission
import kotlinx.android.synthetic.main.activity_detail_package.*
import kotlinx.android.synthetic.main.activity_toolbar.*
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
    lateinit var mTrackId : String

    override fun onActivityReady(savedInstanceState: Bundle?) {
        mDetailPackagePresenter.attachView(this)
        setupUIListener()
        TedRx2Permission.with(this)
                .setPermissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
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

        mDetailPackagePresenter.getPackageDetail(mTrackId)
        mFirebaseDB.gettingCourierLastLocation(mTrackId, object : FirebaseDB.GetFireBaseCallBack{
            override fun onSuccess(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEach {
                    Log.d(javaClass.name,dataSnapshot.toString())
                    mDriverCurrLongi = it.child("driverCurrentLong").value.toString()
                    mDriverCurrLati = it.child("driverCurrentLat").value.toString()
                    Log.d(javaClass.name, mDriverCurrLongi + "," + mDriverCurrLati)

                    if(mGoogleApiClient != null){
                        latLng = LatLng(mDriverCurrLati.toDouble(), mDriverCurrLongi.toDouble())
                        val markerOptions = MarkerOptions()
                        markerOptions.position(latLng!!)
                        markerOptions.title("Courier Current Position")
                        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                        mMap?.clear()
                        currLocationMarker = mMap?.addMarker(markerOptions)
                        //zoom to current position:
                        mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15F))
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


    override fun onDestroy() {
        mFirebaseDB.removeListener()
        super.onDestroy()
    }

}