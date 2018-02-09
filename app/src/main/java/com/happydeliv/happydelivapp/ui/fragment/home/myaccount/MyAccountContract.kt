package com.happydeliv.happydelivapp.ui.fragment.home.myaccount

import com.scoproject.weatherapp.ui.base.BaseView

/**
 * Created by ibnumuzzakkir on 06/02/18.
 * Android Engineer
 * SCO Project
 */
class MyAccountContract{
    interface View : BaseView {
        fun setupContent(email :String, phoneNo :String)
        fun navigateToLoginPage()
    }

    interface UserActionListener {
        fun logout()
    }
}