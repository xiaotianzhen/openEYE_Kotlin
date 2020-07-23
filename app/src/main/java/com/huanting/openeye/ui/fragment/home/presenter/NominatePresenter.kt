package com.huanting.openeye.ui.fragment.home.presenter

import android.util.Log
import com.google.gson.Gson
import com.huanting.openeye.ui.fragment.home.model.biz.INominateBiz
import com.huanting.openeye.ui.fragment.home.model.biz.INominateBizImpl
import com.huanting.openeye.ui.fragment.home.model.entity.discover.ModelVideoSmallCard
import com.huanting.openeye.ui.fragment.home.model.entity.nominate.ModelFollowCard
import com.huanting.openeye.ui.fragment.home.model.entity.nominate.ModelNominateItemList
import com.huanting.openeye.ui.fragment.home.model.entity.nominate.ModelTextCart
import com.huanting.openeye.ui.fragment.home.model.entity.vo.FollowCardVo
import com.huanting.openeye.ui.fragment.home.model.entity.vo.SingTitleViewVo
import com.huanting.openeye.ui.fragment.home.model.entity.vo.TitleViewVo
import com.huanting.openeye.ui.fragment.home.model.entity.vo.VideoCartVo
import com.huanting.openeye.ui.fragment.home.view.INominateView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.json.JSONArray
import org.json.JSONObject

/**
 *Created by yicooll
 * on 2020/7/15
 */
class NominatePresenter {
    private var items = ArrayList<Any>()
    private var mNominateView:INominateView?=null
    private var mNominateBiz:INominateBiz?=null
    constructor(nominateView:INominateView){
        this.mNominateBiz=INominateBizImpl()
        this.mNominateView=nominateView
    }


    fun getNominateData(url:String){

        var observer=object :Observer<Any> {
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
                for (i in 0 until dataArray.length()) {
                    var data=dataArray[i] as JSONObject
                     when(data.getString("type")){
                         "squareCardCollection"->{
                             var  model=gson.fromJson(dataArray[i].toString(),
                                 ModelNominateItemList::class.java)
                             parseCollectCard(model)
                         }
                         "textCard"->{
                            var model=gson.fromJson(dataArray[i].toString(), ModelTextCart::class.java)
                             items.add(SingTitleViewVo(model.data.text))
                         }
                         "videoSmallCard"->{
                             var model=gson.fromJson(dataArray[i].toString(), ModelVideoSmallCard::class.java)
                             parseVideoSmallCard(model)

                         }
                         "followCard"->{
                             var model=gson.fromJson(dataArray[i].toString(), ModelFollowCard::class.java)
                             parseFollowCardTwo(model)
                         }
                     }
                }
                mNominateView?.setNextPageUrl(jsonObject.getString("nextPageUrl"))
                mNominateView?.showNominateView(items)
            }

            override fun onError(e: Throwable?) {
                Log.i("yicooll error", e.toString())
            }
        }
        mNominateBiz?.getNominateData(url,observer)
    }

    private fun parseFollowCardTwo(model: ModelFollowCard) {
        if(model.data.content.data.author is ModelFollowCard.Data.Content.Data.Author){
            items.add(FollowCardVo(model.data.content.data.cover.detail,model.data.content.data.duration,
                model.data.content.data.author.icon,model.data.content.data.author.name+" / #"+model.data.content.data.category,
                model.data.content.data.title,model.data.content.data.description,model.data.content.data.author.name,model.data.content.data.description,
                model.data.content.data.playUrl,model.data.content.data.cover.blurred,model.data.content.data.id))
        }

    }


    fun parseCollectCard(model: ModelNominateItemList){
        items.add(TitleViewVo(model.data.header.title,model.data.header.rightText))

        for(i in model.data.itemList.indices){
              parseFollowCard(model.data.itemList[i])
        }
    }

    fun parseFollowCard(model: ModelNominateItemList.Data.Item){
        items.add(FollowCardVo(model.data.content.data.cover.detail,model.data.content.data.duration,
        model.data.content.data.author.icon,model.data.content.data.author.name+" / #"+model.data.content.data.category,
        model.data.content.data.title,model.data.content.data.description,model.data.content.data.author.name,model.data.content.data.description,
        model.data.content.data.playUrl,model.data.content.data.cover.blurred,model.data.content.data.id))
    }

    fun parseVideoSmallCard(model:ModelVideoSmallCard){
        if(model.data.author !=null){
            items.add(VideoCartVo(model.data.cover.detail,model.data.duration,model.data.title,model.data.author.name+" / #"+model.data.category,
                model.data.author.icon,model.data.author.description,model.data.author.name, model.data.playUrl,
                model.data.cover.blurred,model.data.id,model.data.consumption.collectionCount,model.data.consumption.shareCount))
        }

    }
}