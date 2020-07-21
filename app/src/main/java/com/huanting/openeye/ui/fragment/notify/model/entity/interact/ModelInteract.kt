package com.huanting.openeye.ui.fragment.notify.model.entity.interact

data class ModelInteract(
    val adExist: Boolean,
    val count: Int,
    val itemList: List<Item>,
    val nextPageUrl: String,
    val total: Int
) {
    data class Item(
        val adIndex: Int,
        val `data`: Data,
        val id: Int,
        val tag: Any,
        val type: String
    ) {
        data class Data(
            val actionUrl: String,
            val dataType: String,
            val haveReward: Boolean,
            val id: Int,
            val ifNewest: Boolean,
            val imageUrl: String,
            val joinCount: Int,
            val newestEndTime: Float,
            val showHotSign: Boolean,
            val title: String,
            val viewCount: Int
        )
    }
}