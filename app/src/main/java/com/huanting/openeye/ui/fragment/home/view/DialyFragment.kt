package com.huanting.openeye.ui.fragment.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.huanting.openeye.R
import com.huanting.openeye.base.BaseFragment
import com.huanting.openeye.ui.fragment.home.adapter.DailyAdapter
import com.huanting.openeye.ui.fragment.home.presenter.DailyPresenter
import kotlinx.android.synthetic.main.fragment_dialy.*


private const val ARG_PARAM1 = "param1"


class DialyFragment : BaseFragment(), IDailyView {

    private var param1: Int? = null
    private var presenter: DailyPresenter? = null
    private var data = ArrayList<Any>()
    private var myAdapter: DailyAdapter? = null


    override fun initView() {
        presenter = DailyPresenter(this)
        myAdapter = DailyAdapter(activity!!, data)
        rv_daily.adapter = myAdapter
        rv_daily.layoutManager = LinearLayoutManager(activity)
    }

    override fun initEvent() {
        presenter?.getDailyData()
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
        this.data.clear()
        this.data.addAll(model)
        myAdapter?.notifyDataSetChanged()
    }
}