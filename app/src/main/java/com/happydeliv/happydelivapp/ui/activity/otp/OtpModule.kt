package com.happydeliv.happydelivapp.ui.activity.otp

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
 * Created by ibnumuzzakkir on 05/02/18.
 * Android Engineer
 * SCO Project
 */
@Module
class OtpModule{
    @Provides
    @ActivityScope
    internal fun provideOtpModule(otpActivity: OtpActivity) : OtpContract.View{
        return otpActivity
    }
    @Provides
    @ActivityScope
    internal fun provideOtpPresenter(networkService: NetworkService, compositeDisposable: CompositeDisposable,
                                       schedulerProvider: AppSchedulerProvider,loginSession: LoginSession, gson: Gson) : OtpPresenter{
        return OtpPresenter(networkService,compositeDisposable, schedulerProvider,loginSession, gson)
    }

    @Provides
    @ActivityScope
    internal fun provideActivityNavigation(otpActivity: OtpActivity): ActivityNavigation{
        return ActivityNavigation(otpActivity)
    }
}