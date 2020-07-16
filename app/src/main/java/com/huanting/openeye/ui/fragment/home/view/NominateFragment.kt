package com.huanting.openeye.ui.fragment.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.huanting.openeye.R
import com.huanting.openeye.base.BaseFragment
import com.huanting.openeye.ui.fragment.home.adapter.NominateAdapter
import com.huanting.openeye.ui.fragment.home.presenter.NominatePresenter
import kotlinx.android.synthetic.main.fragment_nominate.*

private const val ARG_PARAM1 = "param1"


class NominateFragment : BaseFragment(),INominateView{

    private var param1: Int? = null
    private var presenter: NominatePresenter?=null
    private var myAdapter: NominateAdapter?=null
    private var data=ArrayList<Any>()
    override fun initView() {
        presenter= NominatePresenter(this)
        myAdapter= NominateAdapter(activity!!,data)
        rv_nominate.adapter= myAdapter
        rv_nominate.layoutManager=LinearLayoutManager(activity)
    }

    override fun initEvent() {
        presenter?.getNominateData()
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

    override fun showNominateView(data:ArrayList<Any>) {
          this.data.clear()
          this.data.addAll(data)
          myAdapter?.notifyDataSetChanged()
    }
}