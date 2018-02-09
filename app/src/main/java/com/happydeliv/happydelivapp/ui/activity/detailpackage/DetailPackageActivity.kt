package com.happydeliv.happydelivapp.ui.activity.detailpackage

import android.os.Bundle
import com.happydeliv.happydelivapp.R
import com.scoproject.newsapp.ui.common.BaseActivity

/**
 * Created by ibnumuzzakkir on 07/02/18.
 * Android Engineer
 * SCO Project
 */
class DetailPackageActivity : BaseActivity(), DetailPackageContract.View{
    override fun onActivityReady(savedInstanceState: Bundle?) {

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_detail_package
    }

}