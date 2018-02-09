package com.happydeliv.happydelivapp.ui.activity.splashscreen

import com.happydeliv.happydelivapp.di.scope.ActivityScope
import com.scoproject.newsapp.ui.common.navigationcontroller.ActivityNavigation
import dagger.Module
import dagger.Provides

/**
 * Created by ibnumuzzakkir on 06/02/18.
 * Android Engineer
 * SCO Project
 */
@Module
class SplashModule{
    @Provides
    @ActivityScope
    internal fun provideSplashActivity(splashActivity: SplashActivity) : SplashContract.View{
        return splashActivity
    }

    @Provides
    @ActivityScope
    internal fun provideActivityNavigation(splashActivity: SplashActivity): ActivityNavigation {
        return ActivityNavigation(splashActivity)
    }
}