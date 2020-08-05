package com.huanting.openeye.ui.fragment.community.view

import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.huanting.openeye.Constant
import com.huanting.openeye.R
import com.huanting.openeye.base.BaseFragment
import com.huanting.openeye.network.UrlConfig
import com.huanting.openeye.ui.fragment.community.adapter.RecommendAdapter
import com.huanting.openeye.ui.fragment.community.presenter.RecommendPresenter
import kotlinx.android.synthetic.main.fragment_recommend.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class RecommendFragment : BaseFragment(), IRecommendView {

    private var param1: String? = null
    private var param2: String? = null

    private var presenter: RecommendPresenter? = null
    private var recommendAdapter: RecommendAdapter? = null
    private var data = ArrayList<Any>()
    private var isLoadMore = false
    private var nextPageUrl = ""

    private var mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                Constant.FRESH_STATE -> {
                    presenter?.getRecommendData(UrlConfig.COMMUNITY_RECOMMEND)
                    rv_recommend.onHeaderRefreshComplete()
                }
                Constant.LOADMORE_STATE -> {
                    isLoadMore = true
                    if (nextPageUrl.isNotEmpty()) {
                        presenter?.getRecommendData(nextPageUrl)
                    }
                    rv_recommend.onFooterRefreshComplete()
                }
            }
        }
    }

    override fun initView() {
        presenter = RecommendPresenter(this)
        recommendAdapter = RecommendAdapter(activity!!, data)
        rv_recommend.setAdapter(recommendAdapter)
        rv_recommend.layoutManager = LinearLayoutManager(activity)
    }

    override fun initEvent() {
        presenter?.getRecommendData(UrlConfig.COMMUNITY_RECOMMEND)
        rv_recommend.setOnHeaderRefreshListener {
            mHandler.sendEmptyMessageDelayed(Constant.FRESH_STATE, Constant.FRESH_DELAYER)
        }
        rv_recommend.setOnFooterRefreshListener {
            mHandler.sendEmptyMessageDelayed(Constant.LOADMORE_STATE, Constant.LOADMORE_DELAYED)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recommend, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecommendFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun showRecommendView(model: ArrayList<Any>) {
        if (!isLoadMore) {
            data.clear()
        }
        data.addAll(model)
        recommendAdapter?.notifyDataSetChanged()
        isLoadMore = false
    }

    override fun setNextPageUrl(pageUrl: String) {
        nextPageUrl = pageUrl
    }
}