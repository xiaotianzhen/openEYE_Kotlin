package com.huanting.openeye.ui.activity.detail.model.entity

data class ModelCommentTitle(
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
        val font: String,
        val text: String
    )
}