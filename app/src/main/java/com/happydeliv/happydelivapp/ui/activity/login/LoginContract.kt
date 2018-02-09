package com.happydeliv.happydelivapp.ui.activity.login

import com.scoproject.weatherapp.ui.base.BaseView

/**
 * Created by ibnumuzzakkir on 05/02/18.
 * Android Engineer
 * SCO Project
 */
class LoginContract{
    interface View : BaseView{
        fun setupUIListener()
        fun showError(content : String)
        fun navigateToOtpPage()
        fun navigateToHomePage()
    }
    interface UserActionListener{
        fun checkIsLogin()
        fun login(phoneNo : String, password :String)
        fun isValidPhoneNo(phoneNo: String) : Boolean
        fun isValidPassword(password: String) : Boolean
    }
}