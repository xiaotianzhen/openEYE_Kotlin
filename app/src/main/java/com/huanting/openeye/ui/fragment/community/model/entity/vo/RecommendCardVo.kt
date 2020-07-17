package com.huanting.openeye.ui.fragment.community.model.entity.vo

/**
 *Created by yicooll
 * on 2020/7/17
 */
data class RecommendCardVo(
    var cover: String,
    var desc: String,
    var avatarUrl: String,
    var nickName: String,
    var collectionCount: Int,
    var imgWidth: Float,
    var imgHeight: Float
)