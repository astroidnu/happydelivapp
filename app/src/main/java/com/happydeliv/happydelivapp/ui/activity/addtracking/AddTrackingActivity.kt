package com.happydeliv.happydelivapp.ui.activity.addtracking

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.Toast
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
import com.happydeliv.happydelivapp.R
import com.scoproject.newsapp.ui.common.BaseActivity
import com.tedpark.tedpermission.rx2.TedRx2Permission
import kotlinx.android.synthetic.main.activity_add_tracking.*
import javax.inject.Inject

/**
 * Created by ibnumuzzakkir on 07/02/18.
 * Android Engineer
 * SCO Project
 */
class AddTrackingActivity : BaseActivity(), AddTrackingContract.View{

    @Inject
    lateinit var mAddTrackingPresenter : AddTrackingPresenter


    var mGoogleApiClient: GoogleApiClient? = null
    var mLocationRequest: LocationRequest? = null
    var mMap: GoogleMap? = null
    var mapFrag: SupportMapFragment? = null
    var latLng : LatLng? = null
    var currLocationMarker : Marker? = null

    override fun onActivityReady(savedInstanceState: Bundle?) {
        mAddTrackingPresenter.attachView(this)
        setupUIListener()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_add_tracking
    }

    override fun showLoading() {
        pb_add_tracking.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_add_tracking.visibility = View.GONE
    }

    override fun setupUIListener() {
        btn_add_tracking_submit.setOnClickListener {
            val mTrackingId = et_add_tracking_id.text.toString()
            mAddTrackingPresenter.addTrackingPackage(mTrackingId)
        }

        imgbtn_back.setOnClickListener { onBackPressed() }
    }

    override fun navigateToHome() {
        onBackPressed()
    }

    override fun showError(content: String) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        mActivityNavigation.navigateToHomePage()
        super.onBackPressed()
    }
}