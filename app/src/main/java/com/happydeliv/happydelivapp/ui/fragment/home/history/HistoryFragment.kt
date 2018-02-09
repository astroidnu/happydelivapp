package com.happydeliv.happydelivapp.ui.fragment.home.history

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.happydeliv.happydelivapp.R
import com.happydeliv.happydelivapp.ui.activity.home.HomeActivity
import com.happydeliv.happydelivapp.ui.common.BaseFragment
import com.happydeliv.happydelivapp.vo.PackageVo
import com.tunaikumobile.app.adapter.setUp
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.android.synthetic.main.item_package.view.*
import javax.inject.Inject

/**
 * Created by ibnumuzzakkir on 06/02/18.
 * Android Engineer
 * SCO Project
 */
class HistoryFragment : BaseFragment(), HistoryContract.View{
    @Inject
    lateinit var mHistoryPresenter : HistoryPresenter

    private val mRvHistory by lazy {
        rv_history
    }

    private val mLinearLayoutManager = LinearLayoutManager(context)

    override fun getLayoutId(): Int {
        return R.layout.fragment_history
    }


    override fun onLoadFragment(saveInstance: Bundle?) {
        (activity as HomeActivity).hideBtnAddPackage()
        mHistoryPresenter.attachView(this)
        mHistoryPresenter.getHistoryList()
    }

    override fun setupAdapter(data : List<PackageVo>) {
        mRvHistory?.setUp(data,R.layout.item_package,{
            tv_package_expedition_name.text = it.name
            tv_package_resi_no.text ="Resi no : " +  it.resi_number
            tv_package_status.text = it.status
            tv_package_track_id.text = "Track id : " + it.track_id
        },{
            (activity as HomeActivity).mActivityNavigation.navigateToDetailPage()
        },mLinearLayoutManager)
    }

    override fun hideEmptyLayout() {
        ll_history_no_package?.visibility = View.GONE
        mRvHistory?.visibility = View.VISIBLE
    }

    override fun showEmptyLayout() {
        ll_history_no_package?.visibility = View.VISIBLE
        mRvHistory?.visibility = View.GONE
    }

    override fun showError(content: String) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show()
    }

    override fun hideLoading() {
        pb_history?.visibility = View.GONE
    }

    override fun showLoading() {
        pb_history?.visibility = View.VISIBLE
    }

    override fun onStop() {
        mHistoryPresenter.detachView()
        super.onStop()
    }


}