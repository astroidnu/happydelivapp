package com.happydeliv.happydelivapp.vo

import com.google.gson.annotations.SerializedName

/**
 * Created by ibnumuzzakkir on 11/02/18.
 * Android Engineer
 * SCO Project
 */
data class DetailPackageVo(
        @SerializedName("company_name") val companyName : String,
        @SerializedName("resi_number") val resiNumber : String,
        @SerializedName("track_id") val trackId : String,
        @SerializedName("courrier_phone") val courierPhone : String,
        @SerializedName("current_lat") val currentLati : String,
        @SerializedName("current_longi") val currentLongi : String,
        @SerializedName("courrier_name") val courierName : String,
        @SerializedName("courrier_photo") val courierPhoto : String
)