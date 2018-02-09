package com.happydeliv.happydelivapp.ui.activity.addtracking

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
class AddTrackingModule{
    @Provides
    @ActivityScope
    internal fun provideAddTackingActivity(addTrackingActivity: AddTrackingActivity) : AddTrackingContract.View{
        return addTrackingActivity
    }

    @Provides
    @ActivityScope
    internal fun provideAddTrackingPresenter(networkService: NetworkService, compositeDisposable: CompositeDisposable,
                                             schedulerProvider: AppSchedulerProvider, loginSession: LoginSession, gson: Gson) : AddTrackingPresenter{
        return AddTrackingPresenter(networkService,compositeDisposable, schedulerProvider, loginSession, gson)
    }

    @Provides
    @ActivityScope
    internal fun provideActivityNavigation(addTrackingActivity: AddTrackingActivity): ActivityNavigation {
        return ActivityNavigation(addTrackingActivity)
    }
}