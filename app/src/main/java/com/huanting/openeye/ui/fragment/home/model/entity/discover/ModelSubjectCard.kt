package com.huanting.openeye.ui.fragment.home.model.entity.discover

data class ModelSubjectCard(
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
        val header: Header,
        val itemList: List<Item>
    ) {
        data class Header(
            val actionUrl: String,
            val cover: Any,
            val font: String,
            val id: Int,
            val label: Any,
            val labelList: Any,
            val rightText: String,
            val subTitle: Any,
            val subTitleFont: Any,
            val textAlign: String,
            val title: String
        )

        data class Item(
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
                val description: Any,
                val id: Int,
                val image: String,
                val shade: Boolean,
                val title: String
            )
        }
    }
}