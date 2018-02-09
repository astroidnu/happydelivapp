package com.happydeliv.happydelivapp.ui.common

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.scoproject.newsapp.ui.common.BasePresenter
import com.scoproject.weatherapp.ui.base.BaseView
import dagger.android.support.AndroidSupportInjection

/**
 * Created by ibnumuzzakkir on 06/02/18.
 * Android Engineer
 * SCO Project
 */
abstract class BaseFragment : Fragment(), BaseView{

    var mView : View? = null

    private var presenter: BasePresenter<*>? = null

    /**
     * Getting Layout ID from activity
     * */

    abstract fun getLayoutId () : Int

    /**
     * This method will be executed after view has been create in fragment
     */

    protected abstract fun onLoadFragment(saveInstance: Bundle?)

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        inflater.let {
            mView  = inflater.inflate(getLayoutId(), container, false)
        }
        return mView
    }

    /**
     * This method will be executed after all views in fragment has rendered
     * */

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        onLoadFragment(savedInstanceState)
    }

    override fun setPresenter(presenter: BasePresenter<*>) {
        this.presenter = presenter
    }
}