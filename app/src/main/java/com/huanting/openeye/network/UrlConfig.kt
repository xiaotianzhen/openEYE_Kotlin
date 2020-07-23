package com.huanting.openeye.network

import com.huanting.openeye.BuildConfig

/**
 *Created by yicooll
 * on 2020/7/13
 */
object UrlConfig {
    private const val URL:String="http://baobab.kaiyanapp.com/api/"
    private const val DEBUG_URL:String="http://baobab.kaiyanapp.com/api/"
    val BASE_URL=if(BuildConfig.DEBUG) DEBUG_URL else URL

    var HOME_DISCOVER= BASE_URL+"v7/index/tab/discovery?udid=fa53872206ed42e3857755c2756ab683fc22d64a&vc=591&vn=6.2.1&size=720X1280&deviceModel=Che1-CL20&first_channel=eyepetizer_zhihuiyun_market&last_channel=eyepetizer_zhihuiyun_market&system_version_code=19"
    var HOME_NOMINATE= BASE_URL+"v5/index/tab/allRec"

}