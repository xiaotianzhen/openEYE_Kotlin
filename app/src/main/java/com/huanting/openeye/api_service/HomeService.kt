package com.huanting.openeye.api_service

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

/**
 *Created by yicooll
 * on 2020/7/14
 */
interface HomeService {

    //注意因为返回的data，每个数组的item的数据结构是有差异的，直接转换会看上去比较乱，而且要保证每个type的属性合并，这里就直接返回string、
    //传入url的原因是接口下一页连接包含在接口返回值，不能和默认的第一页复用
    @GET
    fun  getDiscoverData(@Url url:String):Observable<Any>

    @GET
    fun getNominateData(@Url url:String):Observable<Any>

    @GET
    fun getDailyData(@Url url:String):Observable<Any>

}