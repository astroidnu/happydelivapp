package com.happydeliv.happydelivapp.ui.fragment.home.myaccount

import com.google.gson.Gson
import com.happydeliv.happydelivapp.api.NetworkService
import com.happydeliv.happydelivapp.session.LoginSession
import com.scoproject.newsapp.ui.common.BasePresenter
import com.scoproject.newsapp.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by ibnumuzzakkir on 06/02/18.
 * Android Engineer
 * SCO Project
 */
class MyAccountPresenter  @Inject constructor(private val networkService: NetworkService,
                                              disposable : CompositeDisposable,
                                              scheduler : SchedulerProvider,
                                              val gson : Gson,
                                              val loginSession: LoginSession) : BasePresenter<MyAccountContract.View>(disposable,scheduler), MyAccountContract.UserActionListener{
    override fun logout() {
        loginSession.clear()
        view?.navigateToLoginPage()
    }

}