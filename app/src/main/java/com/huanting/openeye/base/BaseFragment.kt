package com.huanting.openeye.base

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment


abstract class BaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initEvent()
    }

    protected abstract fun initView()
    protected abstract fun initEvent()

    /**
     * toast 消息
     */
    protected fun showToast(msg: String) {
        if (msg != null && !TextUtils.isEmpty(msg)) {
            Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
        }
    }
}