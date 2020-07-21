package com.huanting.openeye.ui.fragment.notify.model.entity.topic

data class ModelTopicCategory(
    val tabInfo: TabInfo
) {
    data class TabInfo(
        val defaultIdx: Int,
        val tabList: List<Tab>
    ) {
        data class Tab(
            val adTrack: Any,
            val apiUrl: String,
            val id: Int,
            val name: String,
            val nameType: Int,
            val tabType: Int
        )
    }
}