package com.scoproject.newsapp.utils

import io.reactivex.Scheduler

/**
 * Created by ibnumuzzakkir on 10/12/17.
 * Android Engineer
 * SCO Project
 */
interface SchedulerProvider {
    fun ui(): Scheduler
    fun computation(): Scheduler
    fun io(): Scheduler
    fun newThread() : Scheduler
}