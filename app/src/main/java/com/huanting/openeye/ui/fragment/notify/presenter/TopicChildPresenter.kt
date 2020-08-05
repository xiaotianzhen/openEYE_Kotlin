package com.huanting.openeye.ui.fragment.notify.presenter

import com.google.gson.Gson
import com.huanting.openeye.ui.fragment.notify.model.biz.ITopicChildBiz
import com.huanting.openeye.ui.fragment.notify.model.biz.ITopicChildBizImpl
import com.huanting.openeye.ui.fragment.notify.model.entity.topic.ModelTopicChild
import com.huanting.openeye.ui.fragment.notify.model.entity.vo.TopicViewVo
import com.huanting.openeye.ui.fragment.notify.view.ITopicChildView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 *Created by yicooll
 * on 2020/7/20
 */
class TopicChildPresenter {

    private var iTopicChildView: ITopicChildView? = null
    private var iTopicChildBiz: ITopicChildBiz? = null

    constructor(view: ITopicChildView) {
        iTopicChildView = view
        iTopicChildBiz = ITopicChildBizImpl()
    }

    fun getTopicChildData(pageUrl: String) {
        var items = ArrayList<TopicViewVo>()
        var obsever = object : Observer<Any> {
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable?) {
            }

            override fun onNext(model: Any?) {
                var gson = Gson()
                var result = gson.toJson(model)
                var data = gson.fromJson(result, ModelTopicChild::class.java)
                for (i in data.itemList.indices) {
                    items.add(
                        TopicViewVo(
                            data.itemList[i].data.icon,
                            data.itemList[i].data.title,
                            data.itemList[i].data.description
                        )
                    )
                }
                if (data.nextPageUrl != null)
                    iTopicChildView?.setNextPageUrl(data.nextPageUrl as String)
                iTopicChildView?.showTopicChildView(items)
            }

            override fun onError(e: Throwable?) {
            }
        }
        iTopicChildBiz?.getTopicChildData(pageUrl, obsever)

    }
}