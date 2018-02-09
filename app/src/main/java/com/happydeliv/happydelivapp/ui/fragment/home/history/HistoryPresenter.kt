package com.happydeliv.happydelivapp.ui.fragment.home.history

import com.google.gson.Gson
import com.happydeliv.happydelivapp.api.NetworkService
import com.happydeliv.happydelivapp.session.LoginSession
import com.happydeliv.happydelivapp.ui.fragment.home.inprogress.InProgressContract
import com.scoproject.newsapp.ui.common.BasePresenter
import com.scoproject.newsapp.utils.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by ibnumuzzakkir on 06/02/18.
 * Android Engineer
 * SCO Project
 */
class HistoryPresenter @Inject constructor(private val networkService: NetworkService,
                                          disposable : CompositeDisposable,
                                          scheduler : SchedulerProvider,
                                          val gson : Gson,
                                          val loginSession: LoginSession) : BasePresenter<HistoryContract.View>(disposable,scheduler), HistoryContract.UserActionListener{
    override fun getHistoryList() {
        view?.showLoading()
        var userInfo = HashMap<String, Any>()
        userInfo.put("phone", loginSession.getPhoneNumber())
        userInfo.put("token", loginSession.getLoginToken())
        disposable.add(
                networkService.getHistoryList(gson.toJson(userInfo))
                        .subscribeOn(scheduler.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            result ->
                            view?.hideLoading()
                            val listPackage = result.data
                            if(listPackage.isNotEmpty()){
                                view?.hideEmptyLayout()
                                view?.setupAdapter(listPackage)
                            }else{
                                view?.showEmptyLayout()
                            }
                        },{
                            view?.hideLoading()
                            view?.showError("please try again")
                        })
        )
    }

}