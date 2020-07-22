package com.huanting.openeye.ui.activity

import androidx.fragment.app.Fragment
import com.huanting.openeye.R
import com.huanting.openeye.base.BaseActivity
import com.huanting.openeye.ui.fragment.main.CommunityFragment
import com.huanting.openeye.ui.fragment.main.HomeFragment
import com.huanting.openeye.ui.fragment.main.NotifyFragment
import com.huanting.openeye.ui.fragment.main.UserFragment
import com.shuyu.gsyvideoplayer.GSYVideoManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    private val mFragments = ArrayList<Fragment>()


    override fun getContentViewLayoutId(): Int {
        return R.layout.activity_main;
    }

    override fun initView() {
    }

    override fun initEvent() {
        changeFragment(HomeFragment::class.simpleName)
        rg_main.setOnCheckedChangeListener { radioGroup, checkId ->
            when (checkId) {
                R.id.rb_main -> {
                    changeFragment(HomeFragment::class.simpleName)
                }
                R.id.rb_community -> {
                    changeFragment(CommunityFragment::class.simpleName)
                }
                R.id.rb_notify -> {
                    changeFragment(NotifyFragment::class.simpleName)
                }
                R.id.rb_user -> {
                    changeFragment(UserFragment::class.simpleName)
                }
            }
        }
    }

    private fun changeFragment(tag: String?) {
        hideFragment()
        var transaction = supportFragmentManager.beginTransaction()
        var fragment = supportFragmentManager.findFragmentByTag(tag)
        if (fragment != null) {
            transaction.show(fragment)
        } else {
            when (tag) {
                HomeFragment::class.simpleName -> {
                    fragment =
                        HomeFragment()
                }
                CommunityFragment::class.simpleName -> {
                    fragment =
                        CommunityFragment()
                }
                NotifyFragment::class.simpleName -> {
                    fragment =
                        NotifyFragment()
                }
                UserFragment::class.simpleName -> {
                    fragment =
                        UserFragment()
                }
            }

            if (fragment != null) {
                mFragments.add(fragment)
                transaction.add(R.id.fl_layout, fragment, tag)
            }
        }
        transaction.commitAllowingStateLoss()
    }

    private fun hideFragment() {
        var transaction = supportFragmentManager.beginTransaction()
        for (f in mFragments) {
            transaction.hide(f)
        }
        transaction.commit()
    }

    override fun onBackPressed() {
        if (GSYVideoManager.backFromWindowFull(this)) {
            return
        }
        super.onBackPressed()
    }
}