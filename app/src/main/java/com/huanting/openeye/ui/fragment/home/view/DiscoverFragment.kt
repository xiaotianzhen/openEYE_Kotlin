package com.huanting.openeye.ui.fragment.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.huanting.openeye.R
import com.huanting.openeye.base.BaseFragment
import com.huanting.openeye.ui.fragment.home.adapter.DiscoverAdapter
import com.huanting.openeye.ui.fragment.home.presenter.DiscoverPresenter
import kotlinx.android.synthetic.main.fragment_discover.*


private const val ARG_PARAM1 = "param1"

class DiscoverFragment : BaseFragment(),IDiscoverView {

    private var param1: Int? = null
    private var mPresenter: DiscoverPresenter?=null

    private var data=ArrayList<Any>()
    private var myAdapter:DiscoverAdapter?=null

    override fun initView() {
        mPresenter= DiscoverPresenter(this)
        myAdapter=DiscoverAdapter(context!!,data)
        rv_discover.adapter=myAdapter
        rv_discover.layoutManager=LinearLayoutManager(context!!)
    }

    override fun initEvent() {
        mPresenter?.getDiscoverData()
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
        return inflater.inflate(R.layout.fragment_discover, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(type: Int) =
            DiscoverFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, type)
                }
            }
    }

    override  fun showDisCoverView(data:ArrayList<Any>) {
        this.data.clear()
        this.data.addAll(data)
        myAdapter?.notifyDataSetChanged()
    }
}