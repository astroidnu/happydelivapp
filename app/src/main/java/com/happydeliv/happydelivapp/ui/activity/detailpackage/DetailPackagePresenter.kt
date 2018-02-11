package com.happydeliv.happydelivapp.ui.activity.detailpackage

import com.google.gson.Gson
import com.happydeliv.happydelivapp.api.NetworkService
import com.happydeliv.happydelivapp.session.LoginSession
import com.scoproject.newsapp.ui.common.BasePresenter
import com.scoproject.newsapp.utils.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
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
    override fun getPackageDetail(trackId : String) {
        view?.showLoading()
        var userInfo = HashMap<String, Any>()
        userInfo.put("phone", loginSession.getPhoneNumber())
        userInfo.put("token", loginSession.getLoginToken())

        var data = HashMap<String, String>()
        data.put("track_id", trackId)
        if(disposable.size() > 0){
            disposable.clear()
        }

        disposable.add(
                networkService.getDetailPackage(gson.toJson(data),gson.toJson(userInfo))
                        .subscribeOn(scheduler.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            result ->
                            view?.hideLoading()
                            if(result.resultCode == 1){
                                var data = result.data
                                view?.setupContent(data.companyName,
                                        data.trackId,
                                        data.courierPhoto,
                                        data.courierName,
                                        data.courierPhone)
                            }else{
                                view?.hideLoading()
                                view?.showError(result.resultMessage)
                            }
                        },{_ ->
                            view?.hideLoading()
                            view?.showError("Please try again")})

        )
    }

}