package com.happydeliv.happydelivapp.ui.fragment.home.myaccount

import android.os.Bundle
import com.happydeliv.happydelivapp.R
import com.happydeliv.happydelivapp.ui.activity.home.HomeActivity
import com.happydeliv.happydelivapp.ui.common.BaseFragment

/**
 * Created by ibnumuzzakkir on 06/02/18.
 * Android Engineer
 * SCO Project
 */
class MyAccountFragment : BaseFragment(), MyAccountContract.View{
    override fun getLayoutId(): Int {
        return R.layout.fragment_my_account
    }

    override fun onLoadFragment(saveInstance: Bundle?) {
        (activity as HomeActivity).hideBtnAddPackage()
    }

}