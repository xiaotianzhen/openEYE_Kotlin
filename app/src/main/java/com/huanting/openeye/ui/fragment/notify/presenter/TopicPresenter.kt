package com.huanting.openeye.ui.fragment.notify.presenter

import android.util.Log
import com.google.gson.Gson
import com.huanting.openeye.ui.fragment.notify.model.biz.ITopicBiz
import com.huanting.openeye.ui.fragment.notify.model.biz.ITopicBizImpl
import com.huanting.openeye.ui.fragment.notify.model.entity.topic.ModelTopicCategory
import com.huanting.openeye.ui.fragment.notify.view.ITopicView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


/**
 *Created by yicooll
 * on 2020/7/20
 */
class TopicPresenter {

    private var iTopicView:ITopicView?=null
    private var iTopicBiz:ITopicBiz?=null

    constructor(view:ITopicView){
        iTopicView=view
        iTopicBiz=ITopicBizImpl()
    }

    fun getTopicData(){

        var items=ArrayList<Any>()
        var observer=object :Observer<Any>{
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable?) {
            }

            override fun onNext(model: Any?) {
                var gson = Gson()
                var result = gson.toJson(model)
                items.clear()
                var model=gson.fromJson(result, ModelTopicCategory::class.java)
                for(i in model.tabInfo.tabList.indices){
                    items.add(model.tabInfo.tabList[i])
                }
                iTopicView?.showTopicView(items)
            }

            override fun onError(e: Throwable?) {
                Log.i("yicooll e",e.toString())
            }
        }
        iTopicBiz?.getTopicData(observer)
    }
}