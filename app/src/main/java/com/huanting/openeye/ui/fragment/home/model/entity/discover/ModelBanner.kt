package com.huanting.openeye.ui.fragment.home.model.entity.discover

data class ModelBanner(
    val adIndex: Int,
    val `data`: Data,
    val id: Int,
    val tag: Any,
    val type: String
) {
    data class Data(
        val actionUrl: String,
        val adTrack: Any,
        val autoPlay: Boolean,
        val dataType: String,
        val description: Any,
        val header: Any,
        val id: Int,
        val image: String,
        val label: Any,
        val labelList: Any,
        val shade: Boolean,
        val title: String
    )
}