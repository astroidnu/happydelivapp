package com.happydeliv.happydelivapp.ui.activity.login

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
class LoginModule{

    @Provides
    @ActivityScope
    internal fun provideLoginActivity(loginActivity: LoginActivity) : LoginContract.View{
        return loginActivity
    }

    @Provides
    @ActivityScope
    internal fun provideLoginPresenter(networkService: NetworkService, compositeDisposable: CompositeDisposable,
                                       schedulerProvider: AppSchedulerProvider, loginSession: LoginSession, gson: Gson) : LoginPresenter{
        return LoginPresenter(networkService,compositeDisposable, schedulerProvider, loginSession, gson)
    }

    @Provides
    @ActivityScope
    internal fun provideActivityNavigation(loginActivity: LoginActivity): ActivityNavigation{
        return ActivityNavigation(loginActivity)
    }
}