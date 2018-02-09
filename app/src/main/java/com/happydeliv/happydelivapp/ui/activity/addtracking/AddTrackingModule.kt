package com.happydeliv.happydelivapp.ui.activity.addtracking

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
class AddTrackingModule{
    @Provides
    @ActivityScope
    internal fun provideAddTackingActivity(addTrackingActivity: AddTrackingActivity) : AddTrackingContract.View{
        return addTrackingActivity
    }

    @Provides
    @ActivityScope
    internal fun provideActivityNavigation(addTrackingActivity: AddTrackingActivity): ActivityNavigation {
        return ActivityNavigation(addTrackingActivity)
    }
}