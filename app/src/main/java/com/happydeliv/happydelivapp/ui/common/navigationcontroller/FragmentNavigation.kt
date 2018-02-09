package com.happydeliv.happydelivapp.ui.common.navigationcontroller

import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.happydeliv.happydelivapp.ui.fragment.home.history.HistoryFragment
import com.happydeliv.happydelivapp.ui.fragment.home.inprogress.InProgressFragment
import com.happydeliv.happydelivapp.ui.fragment.home.myaccount.MyAccountFragment
import javax.inject.Inject

/**
 * Created by ibnumuzzakkir on 06/02/18.
 * Android Engineer
 * SCO Project
 */
class FragmentNavigation @Inject constructor(val activity: AppCompatActivity, val containerId: Int) {
    var mFragmentManager = activity.supportFragmentManager


    /**
     * Navigate To In Progress Page
     * */

    fun navigateToInProgressPage() {
        val mInProgressFragment = InProgressFragment()
        loadFragment(mInProgressFragment)
    }


    /**
     * Navigate To History Page
     * */

    fun navigateToHistoryPage() {
        val mHistoryFragment = HistoryFragment()
        loadFragment(mHistoryFragment)
    }


    /**
     * Navigate To My Account Page
     * */

    fun navigateToMyAccountPage() {
        val mMyAccountFragment = MyAccountFragment()
        loadFragment(mMyAccountFragment)
    }

    /**
     * Load Fragment
     * Handling all load fragment navigation
     * */
    private fun loadFragment(fragment: Fragment?) {
        mFragmentManager.beginTransaction()
                .replace(containerId, fragment)
                .commitAllowingStateLoss()
    }

    /**
     * Get open current fragment
     * */

    fun getOpenFragment(): Fragment {
        return mFragmentManager.findFragmentById(containerId)
    }
}