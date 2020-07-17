package com.huanting.openeye.ui.fragment.community.model.entity.recommend

data class ModelScrollcard(
    val adIndex: Int,
    val `data`: Data,
    val id: Int,
    val tag: Any,
    val type: String
) {
    data class Data(
        val adTrack: Any,
        val count: Int,
        val dataType: String,
        val footer: Any,
        val header: Any,
        val itemList: List<Item>
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
                val bgPicture: String,
                val dataType: String,
                val subTitle: String,
                val title: String
            )
        }
    }
}