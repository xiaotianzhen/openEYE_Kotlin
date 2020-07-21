package com.huanting.openeye.ui.fragment.notify.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.huanting.openeye.R
import com.huanting.openeye.base.BaseFragment
import com.huanting.openeye.ui.fragment.notify.adapter.TopicAdapter
import com.huanting.openeye.ui.fragment.notify.model.entity.vo.TopicViewVo
import com.huanting.openeye.ui.fragment.notify.presenter.TopicChildPresenter
import kotlinx.android.synthetic.main.fragment_topic_child.*


private const val ARG_PARAM1 = "param1"


class TopicChildFragment : BaseFragment(), ITopicChildView {

    private var pageUrl: String? = null
    private var presenter: TopicChildPresenter? = null
    private var myAdapter: TopicAdapter? = null
    private var data = ArrayList<TopicViewVo>()
    override fun initView() {
        presenter = TopicChildPresenter(this)
        myAdapter = TopicAdapter(R.layout.adapter_topic, data)
        rv_topic.adapter = myAdapter
        rv_topic.layoutManager = LinearLayoutManager(activity)
    }

    override fun initEvent() {
        presenter?.getTopicChildData(pageUrl!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pageUrl = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_topic_child, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(pageUrl: String) =
            TopicChildFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, pageUrl)
                }
            }
    }

    override fun showTopicChildView(model: ArrayList<TopicViewVo>) {
        data.clear()
        data.addAll(model)
        myAdapter?.notifyDataSetChanged()
    }
}