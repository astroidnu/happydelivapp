package com.happydeliv.happydelivapp.ui.fragment.home.history

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.happydeliv.happydelivapp.R
import com.happydeliv.happydelivapp.ui.activity.home.HomeActivity
import com.happydeliv.happydelivapp.ui.common.BaseFragment
import com.tunaikumobile.app.adapter.setUp
import kotlinx.android.synthetic.main.fragment_history.*

/**
 * Created by ibnumuzzakkir on 06/02/18.
 * Android Engineer
 * SCO Project
 */
class HistoryFragment : BaseFragment(), HistoryContract.View{
    private val mRvHistory by lazy {
        rv_history
    }

    private var mData  = ArrayList<String>()
    private val mLinearLayoutManager = LinearLayoutManager(context)

    override fun getLayoutId(): Int {
        return R.layout.fragment_history
    }


    override fun onLoadFragment(saveInstance: Bundle?) {
       for(i in 1..10){
           mData.add("data" + i)
       }
        (activity as HomeActivity).hideBtnAddPackage()
        setupListHistory()
    }

    override fun setupListHistory() {
       mRvHistory.setUp(mData,R.layout.item_package,{},{},mLinearLayoutManager)
    }

}