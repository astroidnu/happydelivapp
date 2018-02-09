package com.happydeliv.happydelivapp.ui.activity.home

import com.happydeliv.happydelivapp.R
import com.happydeliv.happydelivapp.di.scope.ActivityScope
import com.happydeliv.happydelivapp.ui.common.navigationcontroller.FragmentNavigation
import com.scoproject.newsapp.ui.common.navigationcontroller.ActivityNavigation
import dagger.Module
import dagger.Provides

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


}