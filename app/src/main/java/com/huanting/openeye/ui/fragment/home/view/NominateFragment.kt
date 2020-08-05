package com.huanting.openeye.ui.fragment.home.view

import android.app.Activity
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
import com.huanting.openeye.ui.activity.detail.model.vo.VideoDetailVo
import com.huanting.openeye.ui.activity.detail.view.VideoDetailActivity
import com.huanting.openeye.ui.fragment.home.adapter.NominateAdapter
import com.huanting.openeye.ui.fragment.home.model.entity.vo.FollowCardVo
import com.huanting.openeye.ui.fragment.home.model.entity.vo.VideoCartVo
import com.huanting.openeye.ui.fragment.home.presenter.NominatePresenter
import com.huanting.openeye.utils.ToActivityHelper
import kotlinx.android.synthetic.main.fragment_nominate.*

private const val ARG_PARAM1 = "param1"


class NominateFragment : BaseFragment(), INominateView {

    private var param1: Int? = null
    private var presenter: NominatePresenter? = null
    private var myAdapter: NominateAdapter? = null
    private var data = ArrayList<Any>()
    private var isLoadMore = false
    private var nextPageUrl = ""

    var mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                Constant.FRESH_STATE -> {
                    presenter?.getNominateData(UrlConfig.HOME_NOMINATE)
                    rv_nominate.onHeaderRefreshComplete()
                }
                Constant.LOADMORE_STATE -> {
                    isLoadMore = true
                    if (nextPageUrl.isNotEmpty())
                        presenter?.getNominateData(nextPageUrl)
                    rv_nominate.onFooterRefreshComplete()
                }
            }

        }
    }

    override fun initView() {
        presenter = NominatePresenter(this)
        myAdapter = NominateAdapter(activity!!, data)
        rv_nominate.setAdapter(myAdapter)
        rv_nominate.layoutManager = LinearLayoutManager(activity)
    }

    override fun initEvent() {
        presenter?.getNominateData(UrlConfig.HOME_NOMINATE)
        rv_nominate.setOnHeaderRefreshListener {
            mHandler.sendEmptyMessageDelayed(Constant.FRESH_STATE, Constant.FRESH_DELAYER)
        }
        rv_nominate.setOnFooterRefreshListener {
            mHandler.sendEmptyMessageDelayed(Constant.LOADMORE_STATE, Constant.LOADMORE_DELAYED)
        }
        myAdapter?.setOnItemClickListener(object : NominateAdapter.OnItemClickListener {
            override fun onFollowCardClick(view: View, position: Int) {
                var bundle = Bundle()
                var model = data[position] as FollowCardVo
                bundle.putSerializable(
                    "detail", VideoDetailVo(
                        model.coverUrl,
                        model.videoTime.toLong(),
                        model.title,
                        model.desc,
                        model.authorUrl!!,
                        model.videoDesc,
                        model.userDesc,
                        model.nickName,
                        model.playerUrl,
                        model.blurredUrl,
                        "",
                        model.videoId,
                        0,
                        0
                    )
                )
                ToActivityHelper.getInstance()!!.toActivity(
                    activity!!,
                    VideoDetailActivity::class.java, bundle
                )
            }

            override fun onSmallVideoClick(view: View, position: Int) {
                var bundle = Bundle()
                var model = data[position] as VideoCartVo
                bundle.putSerializable(
                    "detail", VideoDetailVo(
                        model.cover,
                        model.vidoTime.toLong(),
                        model.title,
                        model.desc,
                        model.authorUrl!!,
                        model.userDesc,
                        model.nickName,
                        "",
                        model.playerUrl,
                        model.blurredUrl,
                        "",
                        model.videoId,
                        model.collectionCount,
                        model.shareCount
                    )
                )
                ToActivityHelper.getInstance()!!.toActivity(
                    activity!!,
                    VideoDetailActivity::class.java, bundle
                )
            }

        })
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

        return inflater.inflate(R.layout.fragment_nominate, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(type: Int) =
            NominateFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, type)
                }
            }
    }

    override fun showNominateView(data: ArrayList<Any>) {
        if (!isLoadMore) {
            this.data.clear()
        }
        this.data.addAll(data)
        myAdapter?.notifyDataSetChanged()
        isLoadMore = false
    }

    override fun setNextPageUrl(pageUrl: String) {
        nextPageUrl = pageUrl
    }
}