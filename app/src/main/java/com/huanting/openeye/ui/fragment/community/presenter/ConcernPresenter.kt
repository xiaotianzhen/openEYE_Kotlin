package com.huanting.openeye.ui.fragment.community.presenter

import android.util.Log
import com.google.gson.Gson
import com.huanting.openeye.ui.fragment.community.model.biz.IConcernBiz
import com.huanting.openeye.ui.fragment.community.model.biz.IConcernBizImpl
import com.huanting.openeye.ui.fragment.community.model.entity.concern.ModelConcern
import com.huanting.openeye.ui.fragment.community.model.entity.vo.ConcernCardVo
import com.huanting.openeye.ui.fragment.community.view.IConcernView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.json.JSONArray
import org.json.JSONObject

/**
 *Created by yicooll
 * on 2020/7/17
 */
class ConcernPresenter {

    private var iConcernView:IConcernView?=null
    private var iConcernBiz:IConcernBiz?=null
    constructor(view:IConcernView){
        iConcernView=view
        iConcernBiz=IConcernBizImpl()
    }

    fun getConcernData(){

        var items=ArrayList<Any>()
        var observer=object : Observer<Any>{
            override fun onComplete() {

            }
            override fun onSubscribe(d: Disposable?) {

            }

            override fun onNext(model: Any?) {
                var gson = Gson()
                var result = gson.toJson(model)
                //Log.i("yicooll result",result)
                items.clear()
                var jsonObject = JSONObject(result)
                var dataArray: JSONArray = jsonObject.getJSONArray("itemList")
                for(i in 0 until dataArray.length()){
                       var data=gson.fromJson(dataArray[i].toString(),ModelConcern::class.java)
                    items.add(ConcernCardVo(data.data.header.icon,data.data.header.issuerName,
                    data.data.header.time,data.data.content.data.title,data.data.content.data.description,
                        data.data.content.data.cover.feed,data.data.content.data.cover.blurred,data.data.content.data.playUrl,
                        data.data.content.data.category,data.data.content.data.author.description,data.data.content.data.id,
                        data.data.content.data.consumption.collectionCount,data.data.content.data.consumption.shareCount,
                        data.data.content.data.consumption.replyCount,data.data.content.data.consumption.realCollectionCount

                    ))
                }
                iConcernView?.showConcernView(items)

            }

            override fun onError(e: Throwable?) {
                Log.d("yicooll", "onError: e"+e.toString())
            }

        }
        iConcernBiz?.getConcernData(observer)
    }

}