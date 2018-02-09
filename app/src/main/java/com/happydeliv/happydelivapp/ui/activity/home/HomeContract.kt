package com.happydeliv.happydelivapp.ui.activity.home

import com.scoproject.weatherapp.ui.base.BaseView

/**
 * Created by ibnumuzzakkir on 06/02/18.
 * Android Engineer
 * SCO Project
 */
class HomeContract{
    interface View : BaseView{
        fun navigateToInProgressPage()
        fun navigateToHistoryPage()
        fun navigateToMyAccountPage()
        fun setupBottomNavigation()
        fun showBtnAddPackage()
        fun hideBtnAddPackage()
        fun setupUIListener()
    }
}