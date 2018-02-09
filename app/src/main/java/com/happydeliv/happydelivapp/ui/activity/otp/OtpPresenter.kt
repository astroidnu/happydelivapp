package com.happydeliv.happydelivapp.ui.activity.otp

import com.google.gson.Gson
import com.happydeliv.happydelivapp.api.NetworkService
import com.happydeliv.happydelivapp.session.LoginSession
import com.scoproject.newsapp.ui.common.BasePresenter
import com.scoproject.newsapp.utils.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by ibnumuzzakkir on 05/02/18.
 * Android Engineer
 * SCO Project
 */
class OtpPresenter @Inject constructor(private val networkService: NetworkService,
                                       disposable : CompositeDisposable,
                                       scheduler : SchedulerProvider,
                                       loginSession: LoginSession,
                                       gson: Gson) : BasePresenter<OtpContract.View>(disposable,scheduler), OtpContract.UserActionListener{

    private val mLoginSession by lazy {
        loginSession
    }

    private val mGson by lazy{
        gson
    }

    private val mNetworkService by lazy {
        networkService
    }

    override fun submitOtp(otp: String) {
        val data = HashMap<String, Any>()
        data.put("phone", mLoginSession.getPhoneNumber())
        data.put("otp", otp)
        view?.showLoading()
        disposable.add(
                mNetworkService.verifyOtp(mGson.toJson(data))
                        .subscribeOn(scheduler.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            result ->
                            view?.hideLoading()
                            if(result.resultCode == 1){
                                mLoginSession.saveToken(result.data.token)
                                mLoginSession.saveEmail(result.data.email)
                                mLoginSession.saveName(result.data.name)
                                mLoginSession.savePhoneNumber(result.data.phone)
                                view?.navigateToHome()
                            }else{
                                view?.showError(result.resultMessage)
                            }
                        },{
                            error ->
                            view?.hideLoading()
                            view?.showError(error.message.toString())
                        })
        )
    }

    override fun resendOtp() {
        //TODO Add action to resend otp by hitting API
    }

}