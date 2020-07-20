package com.huanting.openeye.api_service

import io.reactivex.Observable
import retrofit2.http.GET

/**
 *Created by yicooll
 * on 2020/7/17
 */
interface CommunityService {

    @GET("v7/community/tab/rec")
    fun  getRecommendData():Observable<Any>

    @GET("v6/community/tab/follow")
    fun getConcernData():Observable<Any>
}