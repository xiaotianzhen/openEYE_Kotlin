package com.huanting.openeye.ui.fragment.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.huanting.openeye.R
import com.huanting.openeye.base.BaseFragment
import com.huanting.openeye.ui.fragment.community.view.ConcernFragment
import com.huanting.openeye.ui.fragment.community.view.RecommendFragment
import kotlinx.android.synthetic.main.fragment_community.*
import kotlinx.android.synthetic.main.menu_home_bar.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CommunityFragment : BaseFragment() {


    private var param1: String? = null
    private var param2: String? = null

    private var titles= arrayListOf("推荐","关注")
    private var fragments=ArrayList<Fragment>()
    override fun initView() {
        im_can.visibility=View.GONE
        tab_lalyout.setupWithViewPager(vp_community)
        tab_lalyout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                vp_community.setCurrentItem(p0!!.position,false)
            }

        })

        fragments.clear()
        fragments.add(RecommendFragment())
        fragments.add(ConcernFragment())
        vp_community.adapter=MyAdapter(activity!!.supportFragmentManager)
        vp_community.setCurrentItem(0,false)
    }

    override fun initEvent() {

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
        return inflater.inflate(R.layout.fragment_community, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CommunityFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    inner class MyAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm)
    {
        override fun getItem(position: Int): Fragment {
             return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titles[position]
        }
    }
}