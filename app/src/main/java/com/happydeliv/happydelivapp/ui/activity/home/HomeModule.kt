package com.happydeliv.happydelivapp.ui.activity.home

import com.google.gson.Gson
import com.happydeliv.happydelivapp.R
import com.happydeliv.happydelivapp.api.NetworkService
import com.happydeliv.happydelivapp.di.scope.ActivityScope
import com.happydeliv.happydelivapp.session.LoginSession
import com.happydeliv.happydelivapp.ui.activity.otp.OtpPresenter
import com.happydeliv.happydelivapp.ui.common.navigationcontroller.FragmentNavigation
import com.happydeliv.happydelivapp.ui.fragment.home.history.HistoryFragment
import com.happydeliv.happydelivapp.ui.fragment.home.history.HistoryPresenter
import com.happydeliv.happydelivapp.ui.fragment.home.inprogress.InProgressPresenter
import com.happydeliv.happydelivapp.ui.fragment.home.myaccount.MyAccountPresenter
import com.scoproject.newsapp.ui.common.navigationcontroller.ActivityNavigation
import com.scoproject.newsapp.utils.AppSchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by ibnumuzzakkir on 06/02/18.
 * Android Engineer
 * SCO Project
 */
@Module
class HomeModule{
    @Provides
    @ActivityScope
    internal fun provideHomeActivity(homeActivity: HomeActivity) : HomeContract.View{
        return homeActivity
    }

    @Provides
    @ActivityScope
    internal fun provideActivityNavigation(homeActivity: HomeActivity): ActivityNavigation{
        return ActivityNavigation(homeActivity)
    }


    @Provides @ActivityScope
    internal fun provideFragmentNavigation(homeActivity: HomeActivity): FragmentNavigation {
        return FragmentNavigation(homeActivity, R.id.frame_home)
    }


    /**
     * Provide all fragment presenter
     * */

    @Provides @ActivityScope
    internal  fun provideInProgressPresenter(networkService: NetworkService, compositeDisposable: CompositeDisposable,
                                             schedulerProvider: AppSchedulerProvider, loginSession: LoginSession, gson: Gson) : InProgressPresenter {
        return InProgressPresenter(networkService,compositeDisposable, schedulerProvider, gson,loginSession)

    }

    @Provides @ActivityScope
    internal  fun provideHistoryPresenter(networkService: NetworkService, compositeDisposable: CompositeDisposable,
                                             schedulerProvider: AppSchedulerProvider, loginSession: LoginSession, gson: Gson) : HistoryPresenter {
        return HistoryPresenter(networkService,compositeDisposable, schedulerProvider, gson,loginSession)
    }


    @Provides @ActivityScope
    internal  fun provideMyAccountPresenter(networkService: NetworkService, compositeDisposable: CompositeDisposable,
                                          schedulerProvider: AppSchedulerProvider, loginSession: LoginSession, gson: Gson) : MyAccountPresenter {
        return MyAccountPresenter(networkService,compositeDisposable, schedulerProvider, gson,loginSession)
    }


}