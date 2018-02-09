package com.scoproject.newsapp.ui.common.navigationcontroller

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import com.happydeliv.happydelivapp.ui.activity.addtracking.AddTrackingActivity
import com.happydeliv.happydelivapp.ui.activity.home.HomeActivity
import com.happydeliv.happydelivapp.ui.activity.login.LoginActivity
import com.happydeliv.happydelivapp.ui.activity.otp.OtpActivity
import com.happydeliv.happydelivapp.ui.activity.register.RegisterActivity
import javax.inject.Inject

/**
 * Created by ibnumuzzakkir on 24/01/18.
 * Android Engineer
 * SCO Project
 */

class ActivityNavigation @Inject constructor(val activity:AppCompatActivity){
    /**
     * Navigate To Otp Page
     * */

    fun navigateToOtpPage(){
        val intentOtpPage = newIntent(activity, OtpActivity::class.java)
        activity.startActivity(intentOtpPage)
    }

    /**
     * Navigate to register page
     * */

    fun navigateToRegisterPage(){
        val intentRegisterPage = newIntent(activity, RegisterActivity::class.java)
        activity.startActivity(intentRegisterPage)
    }

    /**
     * Intent to login page
     * */

    fun navigateToLoginPage(){
        val intentLogin = newIntent(activity,LoginActivity::class.java)
        activity.startActivity(intentLogin)
        activity.finish()
    }

    /**
     * Navigate To Home Page
     * */

    fun navigateToHomePage(){
        val intentHomePage = newIntent(activity, HomeActivity::class.java)
        activity.startActivity(intentHomePage)
    }

    /**
     * Navigate To Add Tracking Page
     * */

    fun navigateToAddTrackingPage(){
        val intentAddTracking = newIntent(activity, AddTrackingActivity::class.java)
        activity.startActivity(intentAddTracking)
    }

    /**
     * Intent Common Function
     * Handling new intent
     * */
    fun <T> newIntent(context: Context, cls: Class<T>): Intent {
        return Intent(context, cls)
    }

    fun newIntentUri(label: String, uri: Uri): Intent {
        return Intent(label, uri)
    }
}