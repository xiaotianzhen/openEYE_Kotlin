package com.huanting.openeye.api_service

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

/**
 *Created by yicooll
 * on 2020/7/17
 */
interface CommunityService {

    @GET
    fun  getRecommendData(@Url path:String):Observable<Any>

    @GET
    fun getConcernData(@Url path: String):Observable<Any>
}