package com.happydeliv.happydelivapp.vo

import com.google.gson.annotations.SerializedName

/**
 * Created by ibnumuzzakkir on 09/02/18.
 * Android Engineer
 * SCO Project
 */
data class PackageVo(
        @SerializedName("name") val name :String,
        @SerializedName("resi_number") val resi_number :String,
        @SerializedName("track_id") val track_id :String,
        @SerializedName("status") val status :String
)