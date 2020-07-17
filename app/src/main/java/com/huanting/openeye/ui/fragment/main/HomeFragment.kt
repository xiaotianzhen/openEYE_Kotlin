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
import com.huanting.openeye.ui.fragment.home.view.DialyFragment
import com.huanting.openeye.ui.fragment.home.view.DiscoverFragment
import com.huanting.openeye.ui.fragment.home.view.NominateFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.menu_home_bar.*


class HomeFragment : BaseFragment() {

    companion object {
        const val DISCOVER=0
        const val NOMINATE=1
        const val DAILY=2
    }

    var titles= arrayListOf("发现","推荐","日报")
    var fragments=ArrayList<Fragment>()

    override fun initView() {
        tab_lalyout.setupWithViewPager(vp_home)
        fragments.clear()
        fragments.add(
            DiscoverFragment.newInstance(
                DISCOVER
            ))
        fragments.add(
            NominateFragment.newInstance(
                NOMINATE
            ))
        fragments.add(
            DialyFragment.newInstance(
                DAILY
            ))
        vp_home.adapter=MyAdapter(activity!!.supportFragmentManager)
        vp_home.setCurrentItem(1,false)
    }

    override fun initEvent() {
        tab_lalyout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener{
            override fun onTabSelected(p0: TabLayout.Tab?) {
                vp_home.setCurrentItem(p0!!.position,false)
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabReselected(p0: TabLayout.Tab?) {

            }
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    inner class  MyAdapter:FragmentPagerAdapter{

        constructor(fm:FragmentManager):super(fm){
        }

        override fun getItem(position: Int): Fragment {
           return fragments[position]
        }

        override fun getCount(): Int {
              return  titles.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titles[position]
        }
    }

}