package com.happydeliv.happydelivapp.api

import com.happydeliv.happydelivapp.api.response.BaseApiResponse
import com.happydeliv.happydelivapp.vo.LoginVo
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
    @POST("login")
    fun login(@Field("data") param : String) : Flowable<BaseApiResponse<String>>

    @FormUrlEncoded
    @POST("register")
    fun registration(@Field("data") param : String) : Flowable<BaseApiResponse<String>>

    @FormUrlEncoded
    @POST("verify_otp")
    fun verifyOtp(@Field("data") param : String) : Flowable<BaseApiResponse<VerifyOtpVo>>
}