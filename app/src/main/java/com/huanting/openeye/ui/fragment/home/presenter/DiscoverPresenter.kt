package com.huanting.openeye.ui.fragment.home.presenter

import android.util.Log
import com.google.gson.Gson
import com.huanting.openeye.ui.fragment.home.model.biz.IDiscoverBiz
import com.huanting.openeye.ui.fragment.home.model.biz.IDiscoverBizImpl
import com.huanting.openeye.ui.fragment.home.model.entity.discover.*
import com.huanting.openeye.ui.fragment.home.view.IDiscoverView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.json.JSONArray
import org.json.JSONObject

/**
 *Created by yicooll
 * on 2020/7/14
 */

class DiscoverPresenter {

    var mDiscoverBiz: IDiscoverBiz? = null
    var mDiscoverView: IDiscoverView? = null

    constructor(discoverView: IDiscoverView) {
        mDiscoverBiz = IDiscoverBizImpl()
        mDiscoverView = discoverView
    }


    fun getDiscoverData() {

        var items = ArrayList<Any>()
        var observer = object : Observer<Any> {
            override fun onComplete() {

            }

            override fun onSubscribe(d: Disposable?) {

            }

            override fun onNext(model: Any?) {
                var gson = Gson()
                var result = gson.toJson(model)
                //Log.i("yicooll result",result)
                var isAddBanner = false
                items.clear()
                var jsonObject = JSONObject(result)
                var dataArray: JSONArray = jsonObject.getJSONArray("itemList")
                for (i in 0 until dataArray.length()) {

                    var data: JSONObject = dataArray[i] as JSONObject
                    Log.i("yicooll type", data.getString("type"))
                    when (data.getString("type")) {
                        "horizontalScrollCard"->{
                            var model: ModelTopBanner = gson.fromJson(
                                dataArray[i].toString(),
                                ModelTopBanner::class.java
                            )
                            items.add(model as Object)
                        }

                        "specialSquareCardCollection" -> {

                            var model: ModelCategoryCart = gson.fromJson(
                                dataArray[i].toString(),
                                ModelCategoryCart::class.java
                            )
                            items.add(model as Object)
                        }
                        "columnCardList" -> {
                            var model: ModelSubjectCard =
                                gson.fromJson(dataArray[i].toString(), ModelSubjectCard::class.java)
                            items.add(model as Object)

                        }
                        "textCard" -> {

                            var model: ModelTitleView =
                                gson.fromJson(dataArray[i].toString(), ModelTitleView::class.java)
                            items.add(model as Object)
                        }
                        "banner" -> {
                            var model: ModelBanner =
                                gson.fromJson(dataArray[i].toString(), ModelBanner::class.java)
                            items.add(model as Object)
                        }
                        "videoSmallCard" -> {

                            var model: ModelVideoSmallCard = gson.fromJson(
                                dataArray[i].toString(),
                                ModelVideoSmallCard::class.java
                            )
                            items.add(model as Object)
                        }
                        "briefCard" -> {
                            var model: ModelBriefcard =
                                gson.fromJson(dataArray[i].toString(), ModelBriefcard::class.java)
                            items.add(model as Object)
                        }
                    }

                }
                mDiscoverView?.showDisCoverView(items)
            }

            override fun onError(e: Throwable?) {
                Log.i("yicooll error", e.toString())
            }
        }
        //这里直接传入观察者，就不需要再model获取到数据的时候再传回presenter了
        mDiscoverBiz?.getDiscoverData(observer)
    }
}