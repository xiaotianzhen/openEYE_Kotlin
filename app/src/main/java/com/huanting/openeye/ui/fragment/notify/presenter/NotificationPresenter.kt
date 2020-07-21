package com.huanting.openeye.ui.fragment.notify.presenter

import com.google.gson.Gson
import com.huanting.openeye.ui.fragment.notify.model.biz.INotificationBiz
import com.huanting.openeye.ui.fragment.notify.model.biz.INotificationBizImpl
import com.huanting.openeye.ui.fragment.notify.model.entity.notify.ModelMessage
import com.huanting.openeye.ui.fragment.notify.view.INotificationView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 *Created by yicooll
 * on 2020/7/20
 */

class  NotificationPresenter{

    private var iNotificationView:INotificationView?=null
    private var iNotificationBiz:INotificationBiz?=null

    constructor(view:INotificationView){
        iNotificationView=view
        iNotificationBiz=INotificationBizImpl()
    }

    fun getNotificationData(){

        var observer=object :Observer<Any>{
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable?) {
            }

            override fun onNext(model: Any?) {
                var gson =Gson()
                var retuslt=gson.toJson(model)
                var model=gson.fromJson(retuslt, ModelMessage::class.java)
                iNotificationView?.showNotificationView(model)
            }

            override fun onError(e: Throwable?) {
            }
        }
        iNotificationBiz?.getNotificationData(observer)
    }
}