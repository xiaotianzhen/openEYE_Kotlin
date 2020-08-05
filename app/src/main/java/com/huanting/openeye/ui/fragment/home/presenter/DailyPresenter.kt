package com.huanting.openeye.ui.fragment.home.presenter

import android.util.Log
import com.google.gson.Gson
import com.huanting.openeye.ui.fragment.home.model.biz.IDailyBiz
import com.huanting.openeye.ui.fragment.home.model.biz.IDailyBizImpl
import com.huanting.openeye.ui.fragment.home.model.entity.nominate.ModelFollowCard
import com.huanting.openeye.ui.fragment.home.model.entity.nominate.ModelTextCart
import com.huanting.openeye.ui.fragment.home.model.entity.vo.FollowCardVo
import com.huanting.openeye.ui.fragment.home.model.entity.vo.TitleViewVo
import com.huanting.openeye.ui.fragment.home.view.IDailyView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.json.JSONArray
import org.json.JSONObject

/**
 *Created by yicooll
 * on 2020/7/16
 */
class DailyPresenter {

    var iDailyView:IDailyView?=null
    var iDailyBiz:IDailyBiz?=null
    var items=ArrayList<Any>()
    constructor(view: IDailyView){
        iDailyView=view
        iDailyBiz=IDailyBizImpl()
    }


    fun getDailyData(path:String){

        var observer=object : Observer<Any>{
            override fun onComplete() {

            }

            override fun onSubscribe(d: Disposable?) {

            }

            override fun onNext(model: Any?) {
                var gson = Gson()
                var result = gson.toJson(model)
                items.clear()
                var jsonObject = JSONObject(result)
                var dataArray: JSONArray = jsonObject.getJSONArray("itemList")
                for(i in 0 until dataArray.length()){
                    var data=dataArray[i] as JSONObject
                    when(data.getString("type")){
                        "followCard"->{
                       var model=gson.fromJson(dataArray[i].toString(),ModelFollowCard::class.java)
                            parseFollowCardTwo(model)
                        }
                        "textCard"->{
                            var model=gson.fromJson(dataArray[i].toString(),ModelTextCart::class.java)
                            if(model.data.text!="今日社区精选"){
                                items.add(TitleViewVo(model.data.text,model.data.actionUrl))
                            }
                        }
                    }
                }
                iDailyView?.setNextPageUrl(jsonObject.getString("nextPageUrl"))
                iDailyView?.showDailyView(items)
            }

            override fun onError(e: Throwable?) {
                Log.i("yicooll error", e.toString())
            }
        }
        iDailyBiz?.getDailyDate(path,observer)

    }

    private fun parseFollowCardTwo(model: ModelFollowCard) {
        if(model.data.content.data.author is ModelFollowCard.Data.Content.Data.Author){
            items.add(
                items.add(FollowCardVo(model.data.content.data.cover.detail,model.data.content.data.duration,
                    model.data.content.data.author.icon,model.data.content.data.author.name+" / #"+model.data.content.data.category,
                    model.data.content.data.title,model.data.content.data.description,model.data.content.data.author.description,model.data.content.data.author.name,
                    model.data.content.data.playUrl,model.data.content.data.cover.blurred,model.data.content.data.id))
            )
        }


    }
}