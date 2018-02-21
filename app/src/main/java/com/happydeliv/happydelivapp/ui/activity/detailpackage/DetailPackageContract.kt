package com.happydeliv.happydelivapp.ui.activity.detailpackage

import com.scoproject.weatherapp.ui.base.BaseView

/**
 * Created by ibnumuzzakkir on 07/02/18.
 * Android Engineer
 * SCO Project
 */
class DetailPackageContract{
    interface View : BaseView {
        fun setupUIListener()
        fun setupContent(expeditionName : String,
                         trackingId :String,
                         imageUrl :String,
                         driverName :String,
                         phoneDriver :String)
        fun showLoading()
        fun hideLoading()
        fun showError(content :String)
        fun drawDirection(currentLat: Double?, currentLong: Double?, destinationLat: Double?, destinationLong: Double?)
        fun addMarker(lati :Double, longi :Double, marker :Int, titleMarker : String)
        fun setContentDurationAndDistance(duration:String, distance:String)
    }
    interface UserActionListener {
        fun getPackageDetail(trackId : String)
        fun getTrackingPackageFirebase(trackingID : String)
    }
}