package com.huanting.openeye.ui.fragment.community.model.entity.concern

data class ModelConcern(
    val adIndex: Int,
    val `data`: Data,
    val id: Int,
    val tag: Any,
    val type: String
) {
    data class Data(
        val adTrack: List<Any>,
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
                val ad: Boolean,
                val adTrack: List<Any>,
                val author: Author,
                val brandWebsiteInfo: Any,
                val campaign: Any,
                val category: String,
                val collected: Boolean,
                val consumption: Consumption,
                val cover: Cover,
                val dataType: String,
                val date: Long,
                val description: String,
                val descriptionEditor: String,
                val descriptionPgc: String,
                val duration: Int,
                val favoriteAdTrack: Any,
                val id: Int,
                val idx: Int,
                val ifLimitVideo: Boolean,
                val label: Any,
                val labelList: List<Any>,
                val lastViewTime: Any,
                val library: String,
                val playInfo: List<Any>,
                val playUrl: String,
                val played: Boolean,
                val playlists: Any,
                val promotion: Any,
                val provider: Provider,
                val reallyCollected: Boolean,
                val recallSource: Any,
                val releaseTime: Long,
                val remark: Any,
                val resourceType: String,
                val searchWeight: Int,
                val shareAdTrack: Any,
                val slogan: Any,
                val src: Any,
                val subtitles: List<Any>,
                val tags: List<Tag>,
                val thumbPlayUrl: Any,
                val title: String,
                val titlePgc: String,
                val type: String,
                val waterMarks: Any,
                val webAdTrack: Any,
                val webUrl: WebUrl
            ) {
                data class Author(
                    val adTrack: Any,
                    val approvedNotReadyVideoCount: Int,
                    val description: String,
                    val expert: Boolean,
                    val follow: Follow,
                    val icon: String,
                    val id: Int,
                    val ifPgc: Boolean,
                    val latestReleaseTime: Long,
                    val link: String,
                    val name: String,
                    val recSort: Int,
                    val shield: Shield,
                    val videoNum: Int
                ) {
                    data class Follow(
                        val followed: Boolean,
                        val itemId: Int,
                        val itemType: String
                    )

                    data class Shield(
                        val itemId: Int,
                        val itemType: String,
                        val shielded: Boolean
                    )
                }

                data class Consumption(
                    val collectionCount: Int,
                    val realCollectionCount: Int,
                    val replyCount: Int,
                    val shareCount: Int
                )

                data class Cover(
                    val blurred: String,
                    val detail: String,
                    val feed: String,
                    val homepage: Any,
                    val sharing: Any
                )

                data class Provider(
                    val alias: String,
                    val icon: String,
                    val name: String
                )

                data class Tag(
                    val actionUrl: String,
                    val adTrack: Any,
                    val bgPicture: String,
                    val childTagIdList: Any,
                    val childTagList: Any,
                    val communityIndex: Int,
                    val desc: Any,
                    val haveReward: Boolean,
                    val headerImage: String,
                    val id: Int,
                    val ifNewest: Boolean,
                    val name: String,
                    val newestEndTime: Any,
                    val tagRecType: String
                )

                data class WebUrl(
                    val forWeibo: String,
                    val raw: String
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