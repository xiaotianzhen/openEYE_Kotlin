package com.huanting.openeye.ui.fragment.home.model.biz

import io.reactivex.Observer

/**
 *Created by yicooll
 * on 2020/7/13
 */
 interface IDiscoverBiz {
     fun getDiscoverData(observer: Observer<Any>)
}