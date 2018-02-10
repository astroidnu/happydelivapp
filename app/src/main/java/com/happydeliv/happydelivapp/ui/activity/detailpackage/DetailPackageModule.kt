package com.happydeliv.happydelivapp.ui.activity.detailpackage

import com.google.gson.Gson
import com.happydeliv.happydelivapp.api.NetworkService
import com.happydeliv.happydelivapp.di.scope.ActivityScope
import com.happydeliv.happydelivapp.session.LoginSession
import com.scoproject.newsapp.ui.common.navigationcontroller.ActivityNavigation
import com.scoproject.newsapp.utils.AppSchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by ibnumuzzakkir on 07/02/18.
 * Android Engineer
 * SCO Project
 */
@Module
class DetailPackageModule{
    @Provides
    @ActivityScope
    internal fun provideDetailPackageActivity(detailPackageActivity: DetailPackageActivity) : DetailPackageContract.View{
        return detailPackageActivity
    }


    @Provides
    @ActivityScope
    internal fun provideDetailPackagePresenter(networkService: NetworkService, compositeDisposable: CompositeDisposable,
                                               schedulerProvider: AppSchedulerProvider, loginSession: LoginSession, gson: Gson) : DetailPackagePresenter{
        return DetailPackagePresenter(networkService,compositeDisposable, schedulerProvider, loginSession, gson)
    }


    @Provides
    @ActivityScope
    internal fun provideActivityNavigation(detailPackageActivity: DetailPackageActivity): ActivityNavigation {
        return ActivityNavigation(detailPackageActivity)
    }
}