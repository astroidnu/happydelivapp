package com.happydeliv.happydelivapp.api

import com.happydeliv.happydelivapp.api.response.BaseApiResponse
import com.happydeliv.happydelivapp.vo.LoginVo
import com.happydeliv.happydelivapp.vo.PackageVo
import com.happydeliv.happydelivapp.vo.RegisterVo
import com.happydeliv.happydelivapp.vo.VerifyOtpVo
import io.reactivex.Flowable
import retrofit2.http.*

/**
 * Created by ibnumuzzakkir on 05/02/18.
 * Android Engineer
 * SCO Project
 */
interface NetworkService{

    @FormUrlEncoded
    @POST("/api/login")
    fun login(@Field("data") param : String) : Flowable<BaseApiResponse<String>>

    @FormUrlEncoded
    @POST("/api/register")
    fun registration(@Field("data") param : String) : Flowable<BaseApiResponse<String>>

    @FormUrlEncoded
    @POST("/api/verify_otp")
    fun verifyOtp(@Field("data") param : String) : Flowable<BaseApiResponse<VerifyOtpVo>>

    @FormUrlEncoded
    @POST("/api/track_package")
    fun addTrackPage(@Field("data") data : String, @Field("user_info") userinfo: String) : Flowable<BaseApiResponse<String>>

    @FormUrlEncoded
    @POST("/api/list_package")
    fun getPackageList(@Field("user_info") userinfo: String) : Flowable<BaseApiResponse<List<PackageVo>>>

    @FormUrlEncoded
    @POST("/api/list_history")
    fun getHistoryList(@Field("user_info") userinfo: String) : Flowable<BaseApiResponse<List<PackageVo>>>


}