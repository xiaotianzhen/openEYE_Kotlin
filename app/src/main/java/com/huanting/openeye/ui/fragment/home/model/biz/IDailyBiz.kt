package com.huanting.openeye.ui.fragment.home.model.biz

import io.reactivex.Observer

/**
 *Created by yicooll
 * on 2020/7/16
 */
interface IDailyBiz {

    fun getDailyDate(path:String,observer: Observer<Any>)
}