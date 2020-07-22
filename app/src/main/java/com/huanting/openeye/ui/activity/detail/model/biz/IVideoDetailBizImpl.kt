package com.huanting.openeye.ui.activity.detail.model.biz

import com.huanting.openeye.api_service.VideoDetailService
import com.huanting.openeye.network.RetrofitUtil
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *Created by yicooll
 * on 2020/7/21
 */
class IVideoDetailBizImpl:IVideoDetailBiz {
    override fun getRecommentData(path:String,observer: Observer<Any>) {
        var cilent= RetrofitUtil.getRetorfit2()
        var service=cilent?.create(VideoDetailService::class.java)
        service!!.getRecommentData(path)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }

    override fun getCommentData(path:String,observer: Observer<Any>) {
        var cilent=RetrofitUtil.getRetorfit2()
        var service=cilent?.create(VideoDetailService::class.java)
        service!!.getCommentData(path)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }
}