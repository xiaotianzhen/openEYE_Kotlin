package com.huanting.openeye.ui.fragment.community.model.biz

import io.reactivex.Observer

/**
 *Created by yicooll
 * on 2020/7/17
 */
interface IRecommendBiz {
    fun  getRecommendData(path:String,observer:Observer<Any>)
}