package com.huanting.openeye.api_service

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

/**
 *Created by yicooll
 * on 2020/7/21
 */
interface VideoDetailService {

    @GET
    fun getCommentData(@Url path:String):Observable<Any>

    @GET
    fun getRecommentData(@Url path:String):Observable<Any>
}