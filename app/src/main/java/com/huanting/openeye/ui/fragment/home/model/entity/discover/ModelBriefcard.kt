package com.huanting.openeye.ui.fragment.home.model.entity.discover

data class ModelBriefcard(
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
        val description: String,
        val expert: Boolean,
        val follow: Follow,
        val haveReward: Boolean,
        val icon: String,
        val iconType: String,
        val id: Int,
        val ifNewest: Boolean,
        val ifPgc: Boolean,
        val ifShowNotificationIcon: Boolean,
        val medalIcon: Boolean,
        val newestEndTime: Any,
        val subTitle: Any,
        val switchStatus: Boolean,
        val title: String,
        val uid: Int
    ) {
        data class Follow(
            val followed: Boolean,
            val itemId: Int,
            val itemType: String
        )
    }
}