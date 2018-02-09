package com.happydeliv.happydelivapp.ui.fragment.home.history

import com.happydeliv.happydelivapp.vo.PackageVo
import com.scoproject.weatherapp.ui.base.BaseView

/**
 * Created by ibnumuzzakkir on 06/02/18.
 * Android Engineer
 * SCO Project
 */
class HistoryContract{
    interface View : BaseView{
        fun setupAdapter(data : List<PackageVo>)
        fun hideEmptyLayout()
        fun showEmptyLayout()
        fun hideLoading()
        fun showLoading()
        fun showError(content :String)
    }
    interface UserActionListener{
        fun getHistoryList()
    }
}