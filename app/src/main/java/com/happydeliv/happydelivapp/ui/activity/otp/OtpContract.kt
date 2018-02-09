package com.happydeliv.happydelivapp.ui.activity.otp

import com.scoproject.weatherapp.ui.base.BaseView

/**
 * Created by ibnumuzzakkir on 05/02/18.
 * Android Engineer
 * SCO Project
 */
class OtpContract{
    interface View : BaseView{
        fun showLoading()
        fun hideLoading()
        fun setupUIListener()
        fun showError(content : String)
        fun navigateToHome()
    }

    interface UserActionListener{
        fun submitOtp(otp :String)
        fun resendOtp()
    }
}