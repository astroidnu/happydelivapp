package com.scoproject.weatherapp.ui.base

/**
 * Created by ibnumuzzakkir on 10/12/17.
 * Android Engineer
 * SCO Project
 */
interface IBasePresenter<in V : BaseView> {

    fun attachView(view: V)

    fun detachView()
}