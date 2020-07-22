package com.huanting.openeye.ui.activity.detail.model.vo

/**
 *Created by yicooll
 * on 2020/7/21
 */
import java.io.Serializable

data class VideoDetailVo(
    var coverUrl: String,
    var videoTime: Long,
    var title: String,
    var desc: String,
    var authorUrl: String,
    var videoDesc: String,
    var userDesc: String,
    var nickName: String,
    var palyerUrl: String,
    var blurredUrl: String,
    var category: String,
    var videoId: Int,
    var collectionCount: Int,
    var shareCount: Int
) : Serializable