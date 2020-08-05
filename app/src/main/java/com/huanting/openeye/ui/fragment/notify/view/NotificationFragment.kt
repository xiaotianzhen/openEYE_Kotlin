package com.huanting.openeye.ui.fragment.notify.view

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
import com.huanting.openeye.ui.fragment.notify.adapter.MessageAdapter
import com.huanting.openeye.ui.fragment.notify.model.entity.notify.ModelMessage
import com.huanting.openeye.ui.fragment.notify.presenter.NotificationPresenter
import kotlinx.android.synthetic.main.fragment_notification.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class NotificationFragment : BaseFragment(), INotificationView {
    private var param1: String? = null
    private var param2: String? = null
    private var myAdapter: MessageAdapter? = null
    private var data = ArrayList<ModelMessage.Message>()
    private var presenter: NotificationPresenter? = null

    private var isLoadMore = false
    private var nextPageUrl = ""


    private var mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                Constant.FRESH_STATE -> {
                    presenter?.getNotificationData(UrlConfig.NOTIFICATION_MSG)
                    rv_notify.onHeaderRefreshComplete()
                }
                Constant.LOADMORE_STATE -> {
                    isLoadMore = true
                    if (nextPageUrl.isNotEmpty()) {
                        presenter?.getNotificationData(nextPageUrl)
                    }
                    rv_notify.onFooterRefreshComplete()
                }
            }
        }
    }

    override fun initView() {
        presenter = NotificationPresenter(this)
        myAdapter = MessageAdapter(R.layout.adapter_message, data)
        rv_notify.setAdapter(myAdapter)
        rv_notify.layoutManager = LinearLayoutManager(activity)
    }

    override fun initEvent() {
        presenter?.getNotificationData(UrlConfig.NOTIFICATION_MSG)
        rv_notify.setOnHeaderRefreshListener {
            mHandler.sendEmptyMessageDelayed(Constant.FRESH_STATE, Constant.FRESH_DELAYER)
        }
        rv_notify.setOnFooterRefreshListener {
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
        return inflater.inflate(R.layout.fragment_notification, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NotificationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun showNotificationView(model: ModelMessage) {
        if (!isLoadMore)
            data.clear()
        data.addAll(model.messageList)
        myAdapter?.notifyDataSetChanged()
        isLoadMore=false
    }

    override fun setNextPageUrl(pageUrl: String) {
        nextPageUrl = pageUrl
    }
}