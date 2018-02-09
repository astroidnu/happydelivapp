package com.happydeliv.happydelivapp.ui.fragment.home.history

import com.scoproject.weatherapp.ui.base.BaseView

/**
 * Created by ibnumuzzakkir on 06/02/18.
 * Android Engineer
 * SCO Project
 */
class HistoryContract{
    interface View : BaseView{
        fun setupListHistory()
    }
    interface UserActionListener{

    }
}