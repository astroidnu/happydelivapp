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
                         eta :String,
                         distance : String,
                         phoneDriver :String,
                         chatDriver :String)
    }
    interface UserActionListener {
        fun getPackageDetail(packageId : String)
    }
}