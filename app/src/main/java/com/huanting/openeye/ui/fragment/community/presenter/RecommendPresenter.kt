package com.huanting.openeye.ui.fragment.community.presenter

import android.util.Log
import com.google.gson.Gson
import com.huanting.openeye.ui.fragment.community.model.biz.IRecommendBiz
import com.huanting.openeye.ui.fragment.community.model.biz.IRecommendBizImpl
import com.huanting.openeye.ui.fragment.community.model.entity.recommend.ModelRecommendcard
import com.huanting.openeye.ui.fragment.community.model.entity.recommend.ModelScrollcard
import com.huanting.openeye.ui.fragment.community.model.entity.vo.RecommendCardVo
import com.huanting.openeye.ui.fragment.community.view.IRecommendView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.json.JSONArray
import org.json.JSONObject

/**
 *Created by yicooll
 * on 2020/7/17
 */
class RecommendPresenter {

    private var iRecommendView:IRecommendView?=null
    private var iRecommendBiz:IRecommendBiz?=null

    constructor(view:IRecommendView){
        iRecommendView=view
        iRecommendBiz=IRecommendBizImpl()
    }

    fun getRecommendData(path:String){
        var items=ArrayList<Any>()
        var communitycardList=ArrayList<RecommendCardVo>()
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
                communitycardList.clear()
                var jsonObject = JSONObject(result)
                var dataArray: JSONArray = jsonObject.getJSONArray("itemList")
                for(i in 0 until dataArray.length()){
                    var data=dataArray[i] as JSONObject
                    when(data.getString("type")){
                        "horizontalScrollCard"->{
                       var model=gson.fromJson(dataArray[i].toString(),ModelScrollcard::class.java)
                            if(model.data.itemList[0].data.bgPicture!=null){
                                items.add(model)
                            }
                        }

                        "communityColumnsCard"->{
                            var model=gson.fromJson(dataArray[i].toString(),ModelRecommendcard::class.java)
                            if(model.data.content.data.width>0&&model.data.content.data.height>0){
                                communitycardList.add(RecommendCardVo(model.data.content.data.cover.feed,model.data.content.data.description,
                                model.data.content.data.owner.avatar,model.data.content.data.owner.nickname,model.data.content.data.consumption.collectionCount,
                                model.data.content.data.width,model.data.content.data.height))
                            }
                        }
                    }
                }
                if(communitycardList.size>0){
                    items.add(communitycardList)
                }
                iRecommendView?.setNextPageUrl(jsonObject.getString("nextPageUrl"))
                iRecommendView?.showRecommendView(items)
            }

            override fun onError(e: Throwable?) {
                Log.d("yicooll", "onError: e"+e.toString())
            }

        }
        iRecommendBiz?.getRecommendData(path,observer)
    }
}