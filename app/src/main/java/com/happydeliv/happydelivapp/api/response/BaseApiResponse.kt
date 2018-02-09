package com.happydeliv.happydelivapp.api.response

import com.google.gson.annotations.SerializedName

/**
 * Created by ibnumuzzakkir on 07/02/18.
 * Android Engineer
 * SCO Project
 */
data class BaseApiResponse<T>(
        @SerializedName("result_code") val resultCode : Int,
        @SerializedName("result_message") val resultMessage : String,
        @SerializedName("data") val data : T
)