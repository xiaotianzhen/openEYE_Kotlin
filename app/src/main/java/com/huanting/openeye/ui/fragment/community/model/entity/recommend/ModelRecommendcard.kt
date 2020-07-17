package com.huanting.openeye.ui.fragment.community.model.entity.recommend

data class ModelRecommendcard(
    val adIndex: Int,
    val `data`: Data,
    val id: Int,
    val tag: Any,
    val type: String
) {
    data class Data(
        val adTrack: Any,
        val content: Content,
        val dataType: String,
        val header: Header
    ) {
        data class Content(
            val adIndex: Int,
            val `data`: Data,
            val id: Int,
            val tag: Any,
            val type: String
        ) {
            data class Data(
                val addWatermark: Boolean,
                val area: String,
                val checkStatus: String,
                val city: String,
                val collected: Boolean,
                val consumption: Consumption,
                val cover: Cover,
                val createTime: Long,
                val dataType: String,
                val description: String,
                val height: Float,
                val id: Int,
                val ifMock: Boolean,
                val latitude: Float,
                val library: String,
                val longitude: Float,
                val owner: Owner,
                val privateMessageActionUrl: Any,
                val reallyCollected: Boolean,
                val recentOnceReply: RecentOnceReply,
                val releaseTime: Long,
                val resourceType: String,
                val selectedTime: Long,
                val status: String,
                val tags: List<Tag>,
                val title: String,
                val uid: Int,
                val updateTime: Long,
                val url: String,
                val urls: List<String>,
                val urlsWithWatermark: List<String>,
                val validateResult: String,
                val validateStatus: String,
                val width: Float
            ) {
                data class Consumption(
                    val collectionCount: Int,
                    val realCollectionCount: Int,
                    val replyCount: Int,
                    val shareCount: Int
                )

                data class Cover(
                    val blurred: Any,
                    val detail: String,
                    val feed: String,
                    val homepage: Any,
                    val sharing: Any
                )

                data class Owner(
                    val actionUrl: String,
                    val area: Any,
                    val avatar: String,
                    val birthday: Long,
                    val city: String,
                    val country: String,
                    val cover: String,
                    val description: String,
                    val expert: Boolean,
                    val followed: Boolean,
                    val gender: String,
                    val ifPgc: Boolean,
                    val job: String,
                    val library: String,
                    val limitVideoOpen: Boolean,
                    val nickname: String,
                    val registDate: Long,
                    val releaseDate: Long,
                    val uid: Int,
                    val university: String,
                    val userType: String
                )

                data class RecentOnceReply(
                    val actionUrl: String,
                    val contentType: Any,
                    val dataType: String,
                    val message: String,
                    val nickname: String
                )

                data class Tag(
                    val actionUrl: String,
                    val adTrack: Any,
                    val bgPicture: String,
                    val childTagIdList: Any,
                    val childTagList: Any,
                    val communityIndex: Int,
                    val desc: String,
                    val haveReward: Boolean,
                    val headerImage: String,
                    val id: Int,
                    val ifNewest: Boolean,
                    val name: String,
                    val newestEndTime: Any,
                    val tagRecType: String
                )
            }
        }

        data class Header(
            val actionUrl: String,
            val followType: String,
            val icon: String,
            val iconType: String,
            val id: Int,
            val issuerName: String,
            val labelList: Any,
            val showHateVideo: Boolean,
            val tagId: Int,
            val tagName: Any,
            val time: Long,
            val topShow: Boolean
        )
    }
}