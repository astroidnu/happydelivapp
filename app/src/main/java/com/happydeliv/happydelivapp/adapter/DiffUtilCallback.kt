package com.tunaikumobile.app.adapter

import android.support.v7.util.DiffUtil

/**
 * Created by Nirwanto Maingkolang on 15/01/18.
 * Android Engineer
 */

internal class DiffUtilCallback<ITEM>(private val oldItems: List<ITEM>,
                                      private val newItems: List<ITEM>)
    : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }

}