package com.tunaikumobile.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Nirwanto Maingkolang on 15/01/18.
 * Android Engineer
 */

infix fun ViewGroup.inflate(layoutResId: Int): View =
        LayoutInflater.from(context).inflate(layoutResId, this, false)