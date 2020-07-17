package com.huanting.openeye.ui.fragment.community.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.huanting.openeye.R
import com.huanting.openeye.base.BaseFragment
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

    override fun initView() {
        presenter = RecommendPresenter(this)
        recommendAdapter = RecommendAdapter(activity!!, data)
        rv_recommend.adapter = recommendAdapter
        rv_recommend.layoutManager = LinearLayoutManager(activity)
    }

    override fun initEvent() {
        presenter?.getRecommendData()
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
        data.clear()
        data.addAll(model)
        recommendAdapter?.notifyDataSetChanged()
    }
}