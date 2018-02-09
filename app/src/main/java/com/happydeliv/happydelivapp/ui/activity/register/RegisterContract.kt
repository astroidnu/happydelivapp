package com.happydeliv.happydelivapp.ui.activity.register

import com.scoproject.weatherapp.ui.base.BaseView

/**
 * Created by ibnumuzzakkir on 05/02/18.
 * Android Engineer
 * SCO Project
 */
class  RegisterContract{
    interface View : BaseView{
        fun setupUIListener()
        fun submitRegistration()
        fun showError(content :String)
        fun showLoading()
        fun hideLoading()
        fun navigateToOtpPage()
    }
    interface UserActionListener{
        fun register(fullname :String, noHp :String,email :String, password:String)
    }
}