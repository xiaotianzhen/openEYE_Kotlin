package com.huanting.openeye.ui.fragment.notify.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.huanting.openeye.R
import com.huanting.openeye.base.BaseFragment
import com.huanting.openeye.ui.fragment.notify.adapter.MessageAdapter
import com.huanting.openeye.ui.fragment.notify.model.entity.notify.ModelMessage
import com.huanting.openeye.ui.fragment.notify.presenter.NotificationPresenter
import kotlinx.android.synthetic.main.fragment_notification.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class NotificationFragment : BaseFragment() ,INotificationView{
    private var param1: String? = null
    private var param2: String? = null
    private var myAdapter:MessageAdapter?=null
    private var data=ArrayList<ModelMessage.Message>()
    private var presenter:NotificationPresenter?=null
    override fun initView() {
        presenter= NotificationPresenter(this)
        myAdapter=MessageAdapter(R.layout.adapter_message,data)
        rv_notify.adapter=myAdapter
        rv_notify.layoutManager=LinearLayoutManager(activity)
    }

    override fun initEvent() {
        presenter?.getNotificationData()
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
        data.clear()
        data.addAll(model.messageList)
        myAdapter?.notifyDataSetChanged()
    }
}