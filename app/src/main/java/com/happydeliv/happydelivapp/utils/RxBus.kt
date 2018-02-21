package com.happydeliv.happydelivapp.utils

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by ibnumuzzakkir on 11/9/17.
 * Android Engineer
 * SCO Project
 */
class RxBus{
    private val bus = PublishSubject.create<HashMap<String, Any>>()

    fun send(o: HashMap<String, Any>) {
        bus.onNext(o)
    }

    fun toObservable(): Observable<HashMap<String,Any>> {
        return bus
    }

    fun hasObservers(): Boolean {
        return bus.hasObservers()
    }
}