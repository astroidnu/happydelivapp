package com.happydeliv.happydelivapp.ui.activity.addtracking

import com.scoproject.weatherapp.ui.base.BaseView

/**
 * Created by ibnumuzzakkir on 07/02/18.
 * Android Engineer
 * SCO Project
 */
class AddTrackingContract{
    interface View : BaseView {
        fun showLoading()
        fun hideLoading()
        fun setupUIListener()
        fun showError(content :String)
        fun navigateToHome()
    }

    interface UserActionListener {
        fun addTrackingPackage(trackingId : String)
    }
}