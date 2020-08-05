package com.huanting.openeye.ui.fragment.home.view

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.huanting.openeye.Constant
import com.huanting.openeye.R
import com.huanting.openeye.base.BaseFragment
import com.huanting.openeye.network.UrlConfig
import com.huanting.openeye.ui.fragment.home.adapter.DailyAdapter
import com.huanting.openeye.ui.fragment.home.presenter.DailyPresenter
import kotlinx.android.synthetic.main.fragment_dialy.*


private const val ARG_PARAM1 = "param1"


class DialyFragment : BaseFragment(), IDailyView {

    private var param1: Int? = null
    private var presenter: DailyPresenter? = null
    private var data = ArrayList<Any>()
    private var myAdapter: DailyAdapter? = null
    private var isLoadMore = false;
    private var nextPageUrl = ""

    private var mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                Constant.FRESH_STATE -> {
                    presenter?.getDailyData(UrlConfig.HOME_DIALY)
                    rv_daily.onHeaderRefreshComplete()
                }
                Constant.LOADMORE_STATE -> {
                    isLoadMore = true
                    if (nextPageUrl.isNotEmpty()) {
                        presenter?.getDailyData(nextPageUrl)
                    }
                    rv_daily.onFooterRefreshComplete()
                }
            }
        }
    }

    override fun initView() {
        presenter = DailyPresenter(this)
        myAdapter = DailyAdapter(activity!!, data)
        rv_daily.setAdapter(myAdapter)
        rv_daily.layoutManager = LinearLayoutManager(activity)
    }

    override fun initEvent() {
        presenter?.getDailyData(UrlConfig.HOME_DIALY)
        rv_daily.setOnHeaderRefreshListener {
            mHandler.sendEmptyMessageDelayed(Constant.FRESH_STATE, Constant.FRESH_DELAYER)
        }
        rv_daily.setOnFooterRefreshListener {
            mHandler.sendEmptyMessageDelayed(Constant.LOADMORE_STATE, Constant.LOADMORE_DELAYED)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dialy, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(type: Int) =
            DialyFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, type)
                }
            }
    }

    override fun showDailyView(model: ArrayList<Any>) {
        if (!isLoadMore) {
            this.data.clear()
        }
        this.data.addAll(model)
        myAdapter?.notifyDataSetChanged()
        isLoadMore = false
    }

    override fun setNextPageUrl(pageUrl: String) {
        nextPageUrl = pageUrl
    }
}