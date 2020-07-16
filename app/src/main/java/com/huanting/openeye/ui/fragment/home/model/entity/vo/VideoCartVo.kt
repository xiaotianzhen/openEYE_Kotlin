package com.huanting.openeye.ui.fragment.home.model.entity.vo

/**
 *Created by yicooll
 * on 2020/7/16
 */
data class VideoCartVo(
    var cover: String,
    var vidoTime: Int,
    var title: String,
    var desc: String,
    var authorUrl: String,
    var userDesc: String,
    var nickName: String,
    var playerUrl: String,
    var blurredUrl: String,
    var videoId: Int,
    var collectionCount: Int,
    var shareCount: Int
)