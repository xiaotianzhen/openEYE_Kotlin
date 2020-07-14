package com.huanting.openeye.ui.fragment.home.model.entity.discover

data class ModelCategoryCart(
    val adIndex: Int,
    val `data`: Data,
    val id: Int,
    val type: String
) {
    data class Data(
        val count: Int,
        val dataType: String,
        val header: Header,
        val itemList: List<Item>
    ) {
        data class Header(
            val actionUrl: String,
            val font: String,
            val id: Int,
            val rightText: String,
            val textAlign: String,
            val title: String
        )

        data class Item(
            val adIndex: Int,
            val `data`: Data,
            val id: Int,
            val type: String
        ) {
            data class Data(
                val actionUrl: String,
                val dataType: String,
                val id: Int,
                val image: String,
                val shade: Boolean,
                val title: String
            )
        }
    }
}