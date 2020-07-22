package com.huanting.openeye.ui.activity.detail.presenter

import android.util.Log
import com.google.gson.Gson
import com.huanting.openeye.ui.activity.detail.model.biz.IVideoDetailBiz
import com.huanting.openeye.ui.activity.detail.model.biz.IVideoDetailBizImpl
import com.huanting.openeye.ui.activity.detail.model.entity.ModelCommentTitle
import com.huanting.openeye.ui.activity.detail.model.entity.ModelVideoComment
import com.huanting.openeye.ui.activity.detail.model.vo.VideoCommentVo
import com.huanting.openeye.ui.activity.detail.view.IVideoDetailView
import com.huanting.openeye.ui.fragment.home.model.entity.discover.ModelVideoSmallCard
import com.huanting.openeye.ui.fragment.home.model.entity.nominate.ModelTextCart
import com.huanting.openeye.ui.fragment.home.model.entity.vo.SingTitleViewVo
import com.huanting.openeye.ui.fragment.home.model.entity.vo.VideoCartVo
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.json.JSONObject

/**
 *Created by yicooll
 * on 2020/7/21
 */
class VideoDetailPresenter {

    private var iVideoDetailView:IVideoDetailView?=null
    private var iVideoDetailBiz:IVideoDetailBiz?=null

    private var items=ArrayList<Any>()

    constructor(view:IVideoDetailView){
        iVideoDetailView=view
        iVideoDetailBiz=IVideoDetailBizImpl()
    }

    fun getDetailData(videoId:String){

        var obsever=object : Observer<Any>{
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable?) {
            }

            override fun onNext(model: Any?) {
                items.clear()
                var gson=Gson()
                var result=gson.toJson(model)
                var data=JSONObject(result)
                var array=data.getJSONArray("itemList")
                for(i in 0 until array.length()){
                    var item=array[i] as JSONObject
                    when(item.getString("type")){
                        "textCard"->{
                           var textCartModel=gson.fromJson(array[i].toString(),ModelTextCart::class.java)
                            items.add(SingTitleViewVo(textCartModel.data.text))
                        }
                        "videoSmallCard"->{
                            var modelVideoSmallCard=gson.fromJson(array[i].toString(),ModelVideoSmallCard::class.java)
                            parseVideoSmallCard(modelVideoSmallCard)
                        }
                    }
                }
                getCommentData(videoId)
            }

            override fun onError(e: Throwable?) {
                Log.d("yicooll", "onError: e"+e.toString())
            }
        }
        var path= "http://baobab.kaiyanapp.com/api/v4/video/related?id=$videoId"
        iVideoDetailBiz?.getRecommentData(path,obsever)
    }



    fun parseVideoSmallCard(model: ModelVideoSmallCard){
        if(model.data.author !=null){
            items.add(
                VideoCartVo(model.data.cover.detail,model.data.duration,model.data.title,model.data.author.name+" / #"+model.data.category,
                model.data.author.icon,model.data.author.description,model.data.author.name, model.data.playUrl,
                model.data.cover.blurred,model.data.id,model.data.consumption.collectionCount,model.data.consumption.shareCount)
            )
        }

    }
    fun  getCommentData(videoId:String){

        var obsever=object : Observer<Any>{
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable?) {
            }

            override fun onNext(model: Any?) {
                var gson=Gson()
                var result=gson.toJson(model)
                var data=JSONObject(result)
                var array=data.getJSONArray("itemList")
                for(i in 0 until array.length()){
                    var item=array[i] as JSONObject
                    when(item.getString("type")){
                        "leftAlignTextHeader"->{
                            var commentTitleModel=gson.fromJson(array[i].toString(),ModelCommentTitle::class.java)
                            items.add(SingTitleViewVo(commentTitleModel.data.text))
                        }
                        "reply"->{
                            var commentModel=gson.fromJson(array[i].toString(),ModelVideoComment::class.java)
                            items.add(VideoCommentVo(commentModel.data.user.avatar,
                            commentModel.data.user.nickname,commentModel.data.message,commentModel.data.user.releaseDate,commentModel.data.likeCount))
                        }
                    }
                }
                iVideoDetailView?.showVideoDetailView(items)
            }

            override fun onError(e: Throwable?) {
                Log.d("yicooll", "onError: e"+e.toString())
            }
        }
        var path= "http://baobab.kaiyanapp.com/api/v2/replies/video?videoId=$videoId"
        iVideoDetailBiz?.getCommentData(path,obsever)
    }
}