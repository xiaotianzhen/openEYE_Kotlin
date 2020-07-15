package com.huanting.openeye.ui.fragment.home.model.entity.discover

data class ModelTopBanner(
    val adIndex: Int,
    val `data`: Data,
    val id: Int,
    val tag: Any,
    val type: String
) {
    data class Data(
        val count: Int,
        val dataType: String,
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
                val adTrack: List<Any>,
                val autoPlay: Boolean,
                val dataType: String,
                val description: String,
                val header: Header,
                val id: Int,
                val image: String,
                val label: Label,
                val labelList: List<Any>,
                val shade: Boolean,
                val title: String
            ) {
                data class Header(
                    val actionUrl: Any,
                    val cover: Any,
                    val description: Any,
                    val font: Any,
                    val icon: Any,
                    val id: Int,
                    val label: Any,
                    val labelList: Any,
                    val rightText: Any,
                    val subTitle: Any,
                    val subTitleFont: Any,
                    val textAlign: String,
                    val title: Any
                )

                data class Label(
                    val card: String,
                    val detail: Any,
                    val text: String
                )
            }
        }
    }
}