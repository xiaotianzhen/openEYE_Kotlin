package com.huanting.openeye.api_service

import io.reactivex.Observable
import retrofit2.http.GET

/**
 *Created by yicooll
 * on 2020/7/14
 */
interface HomeService {

    //注意因为返回的data，每个数组的item的数据结构是有差异的，直接转换会看上去比较乱，而且要保证每个type的属性合并，这里就直接返回string、

    @GET("v7/index/tab/discovery?udid=fa53872206ed42e3857755c2756ab683fc22d64a&vc=591&vn=6.2.1&size=720X1280&deviceModel=Che1-CL20&first_channel=eyepetizer_zhihuiyun_market&last_channel=eyepetizer_zhihuiyun_market&system_version_code=19")
    fun  getDiscoverData():Observable<Any>


    @GET("v5/index/tab/allRec")
    fun getNominateData():Observable<Any>

    @GET("v5/index/tab/feed")
    fun getDailyData():Observable<Any>

}