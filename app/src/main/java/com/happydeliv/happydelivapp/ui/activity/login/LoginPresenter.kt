package com.happydeliv.happydelivapp.ui.activity.login

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
class LoginPresenter @Inject constructor(private val networkService: NetworkService,
                                         disposable : CompositeDisposable,
                                         scheduler : SchedulerProvider,
                                         loginSession: LoginSession,
                                         gson: Gson) : BasePresenter<LoginContract.View>(disposable,scheduler),LoginContract.UserActionListener{
    private val mLoginSession by lazy {
        loginSession
    }

    private val mGson by lazy{
        gson
    }

    private val mNetworkService by lazy {
        networkService
    }

    override fun checkIsLogin(){
       if(mLoginSession.getLoginToken().isNotEmpty()){
            view?.navigateToHomePage()
       }
    }

    override fun login(phoneNo: String, password: String) {
        if(!isValidPhoneNo(phoneNo)){
            view?.showError("Invalid Phone No")
        }else if(!isValidPassword(password)){
            view?.showError("Invalid Password")
        }else if(!isValidPassword(password) && !isValidPhoneNo(phoneNo)){
            view?.showError("Invalid Phone No and Password")
        }else{
            var data = HashMap<String, Any>()
            data.put("phone",phoneNo)
            data.put("password", password)
            disposable.add(
                    mNetworkService.login(mGson.toJson(data))
                            .subscribeOn(scheduler.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe ({
                                result ->
                                if(result.resultCode == 1){
                                    mLoginSession.savePhoneNumber(phoneNo)
                                    view?.navigateToOtpPage()
                                }else{
                                    view?.showError(result.resultMessage)
                                }
                            },{ _ ->  view?.showError("Login failed, please try again")
                            })
            )
        }
    }

    override fun isValidPhoneNo(phoneNo: String): Boolean {
        return phoneNo.isNotEmpty()
    }

    override fun isValidPassword(password: String): Boolean {
        return password.isNotEmpty()
    }



}