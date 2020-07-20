package com.huanting.openeye.ui.fragment.community.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.huanting.openeye.R
import com.huanting.openeye.base.BaseFragment
import com.huanting.openeye.ui.fragment.community.adapter.ConcernAdapter
import com.huanting.openeye.ui.fragment.community.model.entity.vo.ConcernCardVo
import com.huanting.openeye.ui.fragment.community.presenter.ConcernPresenter
import kotlinx.android.synthetic.main.fragment_concern.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ConcernFragment : BaseFragment(),IConcernView {

    private var param1: String? = null
    private var param2: String? = null

    private var myAdapter: ConcernAdapter?=null

    private var presenter: ConcernPresenter?=null

    private var data = ArrayList<ConcernCardVo>()

    override fun initView() {
        presenter= ConcernPresenter(this)
        myAdapter= ConcernAdapter(R.layout.adapter_concern_follow,data)
        rv_concern.adapter=myAdapter
        rv_concern.layoutManager=LinearLayoutManager(activity)
    }

    override fun initEvent() {
        presenter?.getConcernData()
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
        return inflater.inflate(R.layout.fragment_concern, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ConcernFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun showConcernView(model: ArrayList<Any>) {
        data.clear()
        data.addAll(model as ArrayList<ConcernCardVo>)
        myAdapter?.notifyDataSetChanged()
    }
}