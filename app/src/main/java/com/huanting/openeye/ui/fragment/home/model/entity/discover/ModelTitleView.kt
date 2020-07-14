package com.huanting.openeye.ui.fragment.home.model.entity.discover

data class ModelTitleView(
    val adIndex: Int,
    val `data`: Data,
    val id: Int,
    val tag: Any,
    val type: String
) {
    data class Data(
        val actionUrl: String,
        val adTrack: Any,
        val dataType: String,
        val follow: Any,
        val id: Int,
        val rightText: String,
        val subTitle: Any,
        val text: String,
        val type: String
    )
}