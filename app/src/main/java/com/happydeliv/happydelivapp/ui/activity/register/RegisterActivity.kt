package com.happydeliv.happydelivapp.ui.activity.register

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.happydeliv.happydelivapp.R
import com.scoproject.newsapp.ui.common.BaseActivity
import kotlinx.android.synthetic.main.activity_register.*
import javax.inject.Inject

/**
 * Created by ibnumuzzakkir on 05/02/18.
 * Android Engineer
 * SCO Project
 */
class RegisterActivity : BaseActivity(), RegisterContract.View{
    @Inject
    lateinit var mRegisterPresenter : RegisterPresenter

    override fun onActivityReady(savedInstanceState: Bundle?) {
        mRegisterPresenter.attachView(this)
        setupUIListener()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun setupUIListener() {
       btn_register_sign_up.setOnClickListener {
           submitRegistration()
       }
    }

    override fun submitRegistration() {
        var mFullName = et_register_fullname.text.toString()
        var mPhoneNo = et_register_no_hp.text.toString()
        var mEmail = et_register_email.text.toString()
        var mPassword = et_register_password.text.toString()
        var mConfirmPassword = et_register_confirm_password.text.toString()

        mRegisterPresenter.register(mFullName,mPhoneNo,mEmail,mPassword)
    }

    override fun showLoading() { pb_register.visibility = View.VISIBLE }

    override fun hideLoading() { pb_register.visibility = View.GONE }

    override fun showError(content: String) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show()
    }

    override fun navigateToOtpPage() {
        mActivityNavigation.navigateToOtpPage()
    }



}