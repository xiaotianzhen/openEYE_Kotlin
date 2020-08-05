package com.huanting.openeye.api_service

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

/**
 *Created by yicooll
 * on 2020/7/20
 */
interface NotificationService {

    @GET
    fun getNotificaitonData(@Url path:String):Observable<Any>

    @GET("v7/tag/tabList")
    fun getTopicData():Observable<Any>

    @GET
    fun getInteractData(@Url path:String):Observable<Any>

     @GET
    fun getTopicChildData(@Url path: String):Observable<Any>
}