package com.huanting.openeye.ui.fragment.community.model.entity.vo

/**
 *Created by yicooll
 * on 2020/7/17
 */
data class ConcernCardVo (
    var avatarUrl:String,
    var issureName:String,
    var releaseTime:Long,
    var title:String,
    var desc:String,
    var coverUrl:String,
    var blurredUrl:String,
    var playUrl:String,
    var category:String,
    var authorDesc:String,
    var videoId:Int,
    var collectionCount:Int,
    var shareCount:Int,
    var replyCount:Int,
    var realCollectionCount:Int
)