package com.happydeliv.happydelivapp.ui.activity.detailpackage

import android.os.Bundle
import android.view.View
import com.happydeliv.happydelivapp.R
import com.scoproject.newsapp.ui.common.BaseActivity
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
        setupUIListener()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_detail_package
    }

    override fun setupUIListener() {
        imgbtn_back.visibility = View.VISIBLE
        imgbtn_back.setOnClickListener {
            this.finish()
        }
    }

    override fun setupContent(expeditionName: String,
                              trackingId: String,
                              imageUrl: String,
                              driverName: String,
                              eta: String,
                              distance: String,
                              phoneDriver: String,
                              chatDriver: String) {

    }

}