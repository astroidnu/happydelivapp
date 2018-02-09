package com.happydeliv.happydelivapp.ui.fragment.home.myaccount

import android.os.Bundle
import com.happydeliv.happydelivapp.R
import com.happydeliv.happydelivapp.session.LoginSession
import com.happydeliv.happydelivapp.ui.activity.home.HomeActivity
import com.happydeliv.happydelivapp.ui.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_my_account.*
import javax.inject.Inject

/**
 * Created by ibnumuzzakkir on 06/02/18.
 * Android Engineer
 * SCO Project
 */
class MyAccountFragment : BaseFragment(), MyAccountContract.View{
    @Inject
    lateinit var mMyAccountPresenter : MyAccountPresenter

    @Inject
    lateinit var mLoginSession : LoginSession

    private val mProfileEmail by lazy{
        tv_profile_email
    }

    private val mProfilePhone by lazy{
        tv_profile_phone
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_my_account
    }

    override fun onLoadFragment(saveInstance: Bundle?) {
        (activity as HomeActivity).hideBtnAddPackage()
        mMyAccountPresenter.attachView(this)
        setupContent(mLoginSession.getEmail(), mLoginSession.getPhoneNumber())
    }

    override fun setupContent(email: String, phoneNo: String) {
        mProfileEmail.text = email
        mProfilePhone.text = phoneNo
    }

    override fun navigateToLoginPage() {
        (activity as HomeActivity).mActivityNavigation.navigateToLoginPage()
    }

}