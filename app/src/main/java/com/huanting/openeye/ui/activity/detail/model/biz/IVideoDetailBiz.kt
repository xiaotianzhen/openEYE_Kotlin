package com.huanting.openeye.ui.activity.detail.model.biz

import io.reactivex.Observer

/**
 *Created by yicooll
 * on 2020/7/21
 */
interface IVideoDetailBiz {
    fun getRecommentData(path:String,observer:Observer<Any>)
    fun getCommentData(path:String,observer:Observer<Any>)
}