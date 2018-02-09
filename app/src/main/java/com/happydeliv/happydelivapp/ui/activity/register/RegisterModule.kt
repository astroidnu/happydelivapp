package com.happydeliv.happydelivapp.ui.activity.register

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
class RegisterModule{
    @Provides
    @ActivityScope
    internal fun provideOtpModule(registerActivity: RegisterActivity) : RegisterContract.View{
        return registerActivity
    }

    @Provides
    @ActivityScope
    internal fun provideRegisterPresenter(networkService: NetworkService, compositeDisposable: CompositeDisposable,
                                          schedulerProvider: AppSchedulerProvider,gson: Gson,loginSession: LoginSession): RegisterPresenter {
        return RegisterPresenter(networkService,compositeDisposable,schedulerProvider,gson,loginSession)
    }

    @Provides
    @ActivityScope
    internal fun provideActivityNavigation(registerActivity: RegisterActivity): ActivityNavigation {
        return ActivityNavigation(registerActivity)
    }
}