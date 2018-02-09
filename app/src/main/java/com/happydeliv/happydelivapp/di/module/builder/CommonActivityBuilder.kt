package com.happydeliv.happydelivapp.di.module.builder

import com.happydeliv.happydelivapp.di.scope.ActivityScope
import com.happydeliv.happydelivapp.ui.activity.addtracking.AddTrackingActivity
import com.happydeliv.happydelivapp.ui.activity.addtracking.AddTrackingModule
import com.happydeliv.happydelivapp.ui.activity.detailpackage.DetailPackageActivity
import com.happydeliv.happydelivapp.ui.activity.detailpackage.DetailPackageModule
import com.happydeliv.happydelivapp.ui.activity.home.HomeActivity
import com.happydeliv.happydelivapp.ui.activity.home.HomeModule
import com.happydeliv.happydelivapp.ui.activity.login.LoginActivity
import com.happydeliv.happydelivapp.ui.activity.login.LoginModule
import com.happydeliv.happydelivapp.ui.activity.otp.OtpActivity
import com.happydeliv.happydelivapp.ui.activity.otp.OtpModule
import com.happydeliv.happydelivapp.ui.activity.register.RegisterActivity
import com.happydeliv.happydelivapp.ui.activity.register.RegisterModule
import com.happydeliv.happydelivapp.ui.activity.splashscreen.SplashActivity
import com.happydeliv.happydelivapp.ui.activity.splashscreen.SplashModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by ibnumuzzakkir on 05/02/18.
 * Android Engineer
 * SCO Project
 */
@Module
abstract class CommonActivityBuilder{
    @ActivityScope
    @ContributesAndroidInjector(modules = [(SplashModule::class)])
    internal abstract fun bindSplashActivity(): SplashActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(LoginModule::class)])
    internal abstract fun bindLoginActivity(): LoginActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(RegisterModule::class)])
    internal abstract fun bindRegisterActivity(): RegisterActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(OtpModule::class)])
    internal abstract fun bindOtpActivity(): OtpActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(HomeModule::class),(HomeActivityBuilder::class)])
    internal abstract fun bindHomeActivity(): HomeActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(DetailPackageModule::class)])
    internal abstract fun bindDetailPackageActivity(): DetailPackageActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(AddTrackingModule::class)])
    internal abstract fun bindAddTrackingActivity(): AddTrackingActivity

}