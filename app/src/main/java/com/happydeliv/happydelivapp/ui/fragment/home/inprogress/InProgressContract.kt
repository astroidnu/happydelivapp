package com.happydeliv.happydelivapp.ui.fragment.home.inprogress

import com.happydeliv.happydelivapp.vo.PackageVo
import com.scoproject.weatherapp.ui.base.BaseView

/**
 * Created by ibnumuzzakkir on 06/02/18.
 * Android Engineer
 * SCO Project
 */
class InProgressContract{
    interface View : BaseView{
        fun setupUIListener()
        fun setupAdapter(data : List<PackageVo>)
        fun hideEmptyLayout()
        fun showEmptyLayout()
        fun hideLoading()
        fun showLoading()
        fun showError(content :String)

    }
    interface UserActionListener{
        fun getTrackingList()
    }
}