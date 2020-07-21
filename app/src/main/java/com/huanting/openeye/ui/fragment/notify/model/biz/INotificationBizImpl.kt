package com.huanting.openeye.ui.fragment.notify.model.biz


import com.huanting.openeye.api_service.NotificationService
import com.huanting.openeye.network.RetrofitUtil
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *Created by yicooll
 * on 2020/7/20
 */
 class INotificationBizImpl:INotificationBiz {
    override fun getNotificationData(observer: Observer<Any>) {
        var cilent= RetrofitUtil.getRetorfit()
        var service=cilent?.create(NotificationService::class.java)
        service!!.getNotificaitonData()
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }
}