package com.huanting.openeye.ui.fragment.notify.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.huanting.openeye.R
import com.huanting.openeye.base.BaseFragment
import com.huanting.openeye.ui.fragment.notify.adapter.InteractAdapter
import com.huanting.openeye.ui.fragment.notify.model.entity.vo.InteractVo
import com.huanting.openeye.ui.fragment.notify.presenter.InteractPresenter
import kotlinx.android.synthetic.main.fragment_interact.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class InteractFragment : BaseFragment(),IInteractView {

    private var param1: String? = null
    private var param2: String? = null
    private var presenter: InteractPresenter?=null
    private var myAdapter: InteractAdapter?=null
    private var data=ArrayList<InteractVo>()
    override fun initView() {
        presenter= InteractPresenter(this)
        myAdapter= InteractAdapter(R.layout.adapter_interact,data)
        rv_interact.adapter=myAdapter
        rv_interact.layoutManager=LinearLayoutManager(activity)
    }

    override fun initEvent() {
        presenter?.getInteractData()
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
        return inflater.inflate(R.layout.fragment_interact, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InteractFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun showInteractView(model: ArrayList<InteractVo>) {
        data.clear()
        data.addAll(model)
        myAdapter?.notifyDataSetChanged()
    }
}