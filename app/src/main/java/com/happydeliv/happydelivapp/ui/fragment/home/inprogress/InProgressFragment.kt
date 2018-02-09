package com.happydeliv.happydelivapp.ui.fragment.home.inprogress

import android.os.Bundle
import com.happydeliv.happydelivapp.R
import com.happydeliv.happydelivapp.ui.activity.home.HomeActivity
import com.happydeliv.happydelivapp.ui.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_in_progress.*

/**
 * Created by ibnumuzzakkir on 06/02/18.
 * Android Engineer
 * SCO Project
 */
class InProgressFragment : BaseFragment(), InProgressContract.View{
    override fun getLayoutId(): Int {
        return R.layout.fragment_in_progress
    }

    override fun onLoadFragment(saveInstance: Bundle?) {
        (activity as HomeActivity).showBtnAddPackage()
        setupUIListener()
    }

    override fun setupUIListener() {
        btn_add_tracking.setOnClickListener {
            (activity as HomeActivity).mActivityNavigation.navigateToAddTrackingPage()
        }
    }

}