package com.happydeliv.happydelivapp.di.module.builder

import com.happydeliv.happydelivapp.ui.fragment.home.history.HistoryFragment
import com.happydeliv.happydelivapp.ui.fragment.home.inprogress.InProgressFragment
import com.happydeliv.happydelivapp.ui.fragment.home.myaccount.MyAccountFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by ibnumuzzakkir on 06/02/18.
 * Android Engineer
 * SCO Project
 */
@Module
abstract class HomeActivityBuilder {
    @ContributesAndroidInjector
    internal abstract fun contributeHistoryFragment(): HistoryFragment

    @ContributesAndroidInjector
    internal abstract fun contributeInProgressFragment(): InProgressFragment

    @ContributesAndroidInjector
    internal abstract fun contributeMyAccountFragment(): MyAccountFragment

}