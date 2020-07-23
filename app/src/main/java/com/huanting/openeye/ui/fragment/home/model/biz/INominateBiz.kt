package com.huanting.openeye.ui.fragment.home.model.biz

import io.reactivex.Observer

/**
 *Created by yicooll
 * on 2020/7/15
 */
interface INominateBiz {

    fun getNominateData(url:String,observer: Observer<Any>)
}