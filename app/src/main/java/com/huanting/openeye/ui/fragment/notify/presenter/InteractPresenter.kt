package com.huanting.openeye.ui.fragment.notify.presenter

import com.google.gson.Gson
import com.huanting.openeye.ui.fragment.notify.model.biz.IInteractBiz
import com.huanting.openeye.ui.fragment.notify.model.biz.IInteractBizImpl
import com.huanting.openeye.ui.fragment.notify.model.entity.interact.ModelInteract
import com.huanting.openeye.ui.fragment.notify.model.entity.vo.InteractVo
import com.huanting.openeye.ui.fragment.notify.view.IInteractView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 *Created by yicooll
 * on 2020/7/20
 */
class InteractPresenter {

    private var iInteractView:IInteractView?=null
    private var iInteractBiz:IInteractBiz?=null

    constructor(view:IInteractView){
        iInteractView=view
        iInteractBiz=IInteractBizImpl()
    }

    fun getInteractData(path:String){
        var items=ArrayList<InteractVo>()
        var observer=object :Observer<Any>{
            override fun onComplete() {

            }

            override fun onSubscribe(d: Disposable?) {
            }

            override fun onNext(model: Any?) {
                items.clear()
                var gson=Gson()
                var result=gson.toJson(model)
                var data=gson.fromJson(result,ModelInteract::class.java)
                for(i in data.itemList.indices){
                    items.add(InteractVo(data.itemList[i].data.imageUrl,data.itemList[i].data.title,data.itemList[i].data.joinCount,data.itemList[i].data.viewCount))
                }
                iInteractView?.setNextPageUrl(data.nextPageUrl)
                iInteractView?.showInteractView(items)
            }

            override fun onError(e: Throwable?) {

            }
        }
        iInteractBiz?.getInteractData(path,observer)
    }
}