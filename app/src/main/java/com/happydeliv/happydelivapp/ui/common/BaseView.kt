package com.scoproject.weatherapp.ui.base

import com.scoproject.newsapp.ui.common.BasePresenter

/**
 * Created by ibnumuzzakkir on 10/12/17.
 * Android Engineer
 * SCO Project
 */
interface BaseView{
    fun setPresenter(presenter: BasePresenter<*>)
}