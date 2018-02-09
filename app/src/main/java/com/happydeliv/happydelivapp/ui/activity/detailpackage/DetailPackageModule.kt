package com.happydeliv.happydelivapp.ui.activity.detailpackage

import com.happydeliv.happydelivapp.di.scope.ActivityScope
import com.scoproject.newsapp.ui.common.navigationcontroller.ActivityNavigation
import dagger.Module
import dagger.Provides

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
    internal fun provideActivityNavigation(detailPackageActivity: DetailPackageActivity): ActivityNavigation {
        return ActivityNavigation(detailPackageActivity)
    }
}