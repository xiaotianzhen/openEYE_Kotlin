package com.huanting.openeye.ui.fragment.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.huanting.openeye.R
import com.huanting.openeye.base.BaseFragment



private const val ARG_PARAM1 = "param1"


class DialyFragment : BaseFragment(){

    private var param1: Int? = null


    override fun initView() {

    }

    override fun initEvent() {

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
}