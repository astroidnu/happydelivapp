package com.scoproject.newsapp.ui.common

import android.arch.lifecycle.*
import com.scoproject.newsapp.utils.SchedulerProvider
import com.scoproject.weatherapp.ui.base.BaseView
import com.scoproject.weatherapp.ui.base.IBasePresenter
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

/**
 * Created by ibnumuzzakkir on 10/12/17.
 * Android Engineer
 * SCO Project
 */
open class BasePresenter<V : BaseView> constructor(var disposable: CompositeDisposable,
                                                   var scheduler: SchedulerProvider) : IBasePresenter<V>, LifecycleObserver, ViewModel() {
    private var weakReference: WeakReference<V>? = null

    override fun attachView(view: V) {
        if (!isViewAttached) {
            weakReference = WeakReference(view)
            view.setPresenter(this)
        }
        if (view is LifecycleOwner) {
            (view as LifecycleOwner).lifecycle.addObserver(this)
        }
    }

    override fun detachView() {
        weakReference?.clear()
        weakReference = null
        disposable.clear()
    }

    val view: V?
        get() = weakReference?.get()

    private val isViewAttached: Boolean
        get() = weakReference != null && weakReference!!.get() != null

}
