package com.happydeliv.happydelivapp.ui.activity.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.iid.FirebaseInstanceId
import com.happydeliv.happydelivapp.R
import com.happydeliv.happydelivapp.session.LoginSession
import com.scoproject.newsapp.ui.common.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

/**
 * Created by ibnumuzzakkir on 05/02/18.
 * Android Engineer
 * SCO Project
 */
class LoginActivity : BaseActivity(), LoginContract.View{
    @Inject
    lateinit var mLoginPresenter : LoginPresenter

    @Inject
    lateinit var mLoginSession : LoginSession

    private val mEtLoginUserName by lazy { et_login_username }

    private val mEtPassword by lazy { et_login_password }

    private val mTvSignUpHere by lazy { tv_sign_up_here }

    private val mBtnSignIn by lazy { btn_sign_in }

    override fun onActivityReady(savedInstanceState: Bundle?) {
        mLoginPresenter.attachView(this)
        val fbToken =  FirebaseInstanceId.getInstance().token
        fbToken?.let {
            mLoginSession.saveFirebaseToken(it)
        }
        mLoginPresenter.checkIsLogin()
        setupUIListener()
    }

    override fun setupUIListener() {
        mBtnSignIn.setOnClickListener {
            val mUsername = mEtLoginUserName.text.toString()
            val mPassword = mEtPassword.text.toString()
            mLoginPresenter.login(mUsername, mPassword)
        }
        mTvSignUpHere.setOnClickListener { mActivityNavigation.navigateToRegisterPage() }
    }

    override fun getLayoutId(): Int {
       return R.layout.activity_login
    }

    override fun showError(content: String) {
        Toast.makeText(applicationContext, content, Toast.LENGTH_SHORT).show()
    }

    override fun navigateToOtpPage() {
        mActivityNavigation.navigateToOtpPage()
    }

    override fun navigateToHomePage() {
        mActivityNavigation.navigateToHomePage()
    }

    override fun showLoading() {
        pb_login.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_login.visibility = View.GONE
    }

}