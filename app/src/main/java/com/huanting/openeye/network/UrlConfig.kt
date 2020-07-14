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
}