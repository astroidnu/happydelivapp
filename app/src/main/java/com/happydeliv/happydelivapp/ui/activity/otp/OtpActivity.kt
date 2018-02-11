package com.happydeliv.happydelivapp.ui.activity.otp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.happydeliv.happydelivapp.R
import com.scoproject.newsapp.ui.common.BaseActivity
import kotlinx.android.synthetic.main.activity_otp.*
import javax.inject.Inject

/**
 * Created by ibnumuzzakkir on 05/02/18.
 * Android Engineer
 * SCO Project
 */
class OtpActivity : BaseActivity(), OtpContract.View{
    @Inject
    lateinit var mOtpPresenter : OtpPresenter

    override fun onActivityReady(savedInstanceState: Bundle?) {
        mOtpPresenter.attachView(this)
        setupUIListener()
    }

    override fun getLayoutId(): Int {
      return R.layout.activity_otp
    }

    override fun showLoading() {
       pb_otp.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_otp.visibility = View.GONE
    }

    override fun setupUIListener() {
        btn_otp_submit.setOnClickListener {
            val otp = pin_otp.text.toString()
            mOtpPresenter.submitOtp(otp)
        }
        tv_otp_resend.setOnClickListener {
            mOtpPresenter.resendOtp()
        }
    }

    override fun navigateToHome() {
        mActivityNavigation.navigateToHomePage()
    }

    override fun showError(content: String) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show()
    }


}