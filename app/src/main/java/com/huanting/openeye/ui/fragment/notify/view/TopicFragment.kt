package com.huanting.openeye.ui.fragment.notify.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.huanting.openeye.R
import com.huanting.openeye.base.BaseFragment
import com.huanting.openeye.ui.fragment.notify.model.entity.topic.ModelTopicCategory
import com.huanting.openeye.ui.fragment.notify.presenter.TopicPresenter
import kotlinx.android.synthetic.main.fragment_topic.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class TopicFragment : BaseFragment(),ITopicView {

    private var param1: String? = null
    private var param2: String? = null
    private var presenter:TopicPresenter?=null
    private var fragments=ArrayList<Fragment>()
    private var data=ArrayList<Any>()

    override fun initView() {
        presenter= TopicPresenter(this)
        tab_lalyout.setupWithViewPager(vp_topic)
        tab_lalyout.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                vp_topic.setCurrentItem(p0!!.position,false)
            }

        })

    }

    override fun initEvent() {
        presenter?.getTopicData()
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
        return inflater.inflate(R.layout.fragment_topic, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TopicFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun showTopicView(model: ArrayList<Any>) {
        data.clear()
        fragments.clear()
        data.addAll(model)
        for(i in 0 until data.size){
            var data=data[i] as ModelTopicCategory.TabInfo.Tab
            fragments.add(TopicChildFragment.newInstance(data.apiUrl))
        }
        if(fragments.size>0){
            var adapter=MyAdapter(activity!!.supportFragmentManager)
            vp_topic.adapter=adapter
            vp_topic.setCurrentItem(0,false)
        }

    }

    inner class  MyAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            var data=data[position] as ModelTopicCategory.TabInfo.Tab
            return data.name
        }
    }
}