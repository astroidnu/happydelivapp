package com.happydeliv.happydelivapp.ui.activity.register

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
class RegisterPresenter @Inject constructor(private val networkService: NetworkService,
                                            disposable : CompositeDisposable,
                                            scheduler : SchedulerProvider,
                                            gson :Gson,
                                            loginSession: LoginSession) : BasePresenter<RegisterContract.View>(disposable,scheduler), RegisterContract.UserActionListener{

    private val mLoginSession by lazy {
        loginSession
    }

    private val mGson by lazy{
        gson
    }

    private val mNetworkService by lazy {
        networkService
    }

    override fun register(fullname: String, noHp: String, email: String, password: String) {
        view?.showLoading()
        val data = HashMap<String, Any>()
        data.put("name", fullname)
        data.put("phone", noHp)
        data.put("email",email)
        data.put("password",password)
        mNetworkService.registration(mGson.toJson(data))
                .subscribeOn(scheduler.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({
                    result ->
                    view?.hideLoading()
                    if(result.resultCode == 1){
                        mLoginSession.savePhoneNumber(noHp)
                        view?.navigateToOtpPage()
                    }else{
                        view?.showError(result.resultMessage)
                    }
                },{
                    _ ->
                    view?.hideLoading()
                    view?.showError("Server bermasalah, Silahkan coba kembali")
                })
    }

}