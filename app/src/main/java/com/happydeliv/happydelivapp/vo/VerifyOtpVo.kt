package com.happydeliv.happydelivapp.vo

import com.google.gson.annotations.SerializedName

/**
 * Created by ibnumuzzakkir on 09/02/18.
 * Android Engineer
 * SCO Project
 */
data class VerifyOtpVo(
        @SerializedName("email") val email :String,
        @SerializedName("phone") val phone :String,
        @SerializedName("name") val name :String,
        @SerializedName("token") val token :String
)