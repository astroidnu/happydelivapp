package com.happydeliv.happydelivapp.ui.activity.detailpackage

import com.google.gson.Gson
import com.happydeliv.happydelivapp.api.NetworkService
import com.happydeliv.happydelivapp.session.LoginSession
import com.scoproject.newsapp.ui.common.BasePresenter
import com.scoproject.newsapp.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by ibnumuzzakkir on 07/02/18.
 * Android Engineer
 * SCO Project
 */
class DetailPackagePresenter @Inject constructor(private val networkService: NetworkService,
                                                 disposable : CompositeDisposable,
                                                 scheduler : SchedulerProvider,
                                                 val loginSession: LoginSession,
                                                 val gson: Gson) : BasePresenter<DetailPackageContract.View>(disposable,scheduler),DetailPackageContract.UserActionListener{
    override fun getPackageDetail(packageId : String) {

    }

}