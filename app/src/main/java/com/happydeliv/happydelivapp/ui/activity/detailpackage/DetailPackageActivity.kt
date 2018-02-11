package com.happydeliv.happydelivapp.ui.activity.detailpackage

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.happydeliv.happydelivapp.R
import com.scoproject.newsapp.ui.common.BaseActivity
import kotlinx.android.synthetic.main.activity_detail_package.*
import kotlinx.android.synthetic.main.activity_toolbar.*
import javax.inject.Inject

/**
 * Created by ibnumuzzakkir on 07/02/18.
 * Android Engineer
 * SCO Project
 */
class DetailPackageActivity : BaseActivity(), DetailPackageContract.View{
    @Inject
    lateinit var mDetailPackagePresenter : DetailPackagePresenter

    override fun onActivityReady(savedInstanceState: Bundle?) {
        mDetailPackagePresenter.attachView(this)
        setupUIListener()
        val bundle = intent.extras
        if (bundle != null) {
            val mTrackId = bundle.getString("data")
            mDetailPackagePresenter.getPackageDetail(mTrackId)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_detail_package
    }

    override fun setupUIListener() {
        imgbtn_back.visibility = View.VISIBLE
        imgbtn_back.setOnClickListener {
           onBackPressed()
        }
    }

    override fun setupContent(expeditionName: String,
                              trackingId: String,
                              imageUrl: String,
                              driverName: String,
                              phoneDriver: String) {
        tv_company_name.text = expeditionName
        tv_courier_name.text = driverName
        tv_detail_track_id.text = "Track id : " + trackingId
        Glide.with(this)
                .load(imageUrl)
                .into(iv_courier_photo)
    }

    override fun onBackPressed() {
        mActivityNavigation.navigateToHomePage()
        super.onBackPressed()
    }

    override fun showLoading() {
        pb_detail_package.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_detail_package.visibility = View.GONE
    }

    override fun showError(content: String) {
        Toast.makeText(this, content, Toast.LENGTH_LONG).show()
    }


}