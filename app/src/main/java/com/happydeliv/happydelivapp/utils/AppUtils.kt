package com.scoproject.newsapp.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by ibnumuzzakkir on 27/01/18.
 * Android Engineer
 * SCO Project
 */
class AppUtils{

    /**
     * Checking Internet Connection
     * */

    fun isConnectingToInternet(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}