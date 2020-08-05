package com.huanting.openeye.ui.activity.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.huanting.openeye.R
import com.huanting.openeye.ui.activity.detail.model.vo.VideoCommentVo
import com.huanting.openeye.ui.activity.detail.model.vo.VideoDetailVo
import com.huanting.openeye.ui.fragment.home.model.entity.vo.SingTitleViewVo
import com.huanting.openeye.ui.fragment.home.model.entity.vo.VideoCartVo
import com.huanting.openeye.utils.ImageUtils


/**
 *Created by yicooll
 * on 2020/7/21
 */
class VideoDetailAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private val SINGTITLE = 1
    private val VIDEOCARD = 2
    private val VIDEOCOMMENT = 3
    private val HEADVIEW = 4
    private var data = ArrayList<Any>()
    private var mContext: Context? = null
    private var videoModel: VideoDetailVo? = null
    private var onClick: OnRecommendClickListener? = null

    fun setOnRecommendClickListener(listener: OnRecommendClickListener) {
        this.onClick = listener
    }

    constructor(context: Context, data: ArrayList<Any>, headData: VideoDetailVo) {
        this.data = data
        mContext = context
        videoModel = headData
    }

    fun updataHead(headData: VideoDetailVo) {
        videoModel = headData
        notifyItemChanged(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            SINGTITLE -> {
                return SingTitleHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.adapter_detail_singtitle, parent, false)
                )
            }
            VIDEOCARD -> {
                return VideoCardHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.adapter_small_video, parent, false)
                )
            }
            VIDEOCOMMENT -> {
                return VideoCommentHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.adapter_detail_comment, parent, false)
                )
            }
            HEADVIEW -> {
                return HeadViedoHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.adapter_detail_head, parent, false)
                )
            }
        }
        return SingTitleHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_detail_singtitle, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (position == 0) {
            var myHolder = holder as HeadViedoHolder
            myHolder.tvTitle!!.text = videoModel!!.title
            myHolder.tvCategory!!.text = videoModel!!.category
            myHolder.tvVideoDesc!!.text = videoModel!!.desc
            myHolder.tvCollectCount!!.text = videoModel!!.collectionCount.toString()
            myHolder.tvShareCount!!.text = videoModel!!.shareCount.toString()
            myHolder.tvNidkName!!.text = videoModel!!.nickName
            myHolder.tvAuthorDesc!!.text = videoModel!!.userDesc
            if (videoModel!!.authorUrl != null) {
                ImageUtils.loadImage(myHolder.imAuthor!!, videoModel!!.authorUrl)
            } else {
                ImageUtils.loadImage(myHolder.imAuthor!!, videoModel!!.blurredUrl)
            }
        } else if (data[position - 1] is SingTitleViewVo) {
            var model = data[position - 1] as SingTitleViewVo
            var myHolder = holder as SingTitleHolder
            myHolder.tvTitle!!.text = model.title + "  >"

        } else if (data[position - 1] is VideoCartVo) {
            var myHolder = holder as VideoCardHolder
            var model: VideoCartVo = data[position - 1] as VideoCartVo
            myHolder.tvTitle?.text = model.title
            myHolder.tvDesc?.text = model.desc
            Glide.with(mContext!!).load(model.cover).into(myHolder.imCover!!)
        } else if (data[position - 1] is VideoCommentVo) {
            var model = data[position - 1] as VideoCommentVo
            var myHolder = holder as VideoCommentHolder
            ImageUtils.loadImageCircle(myHolder!!.tvAvatar!!, model.avatar)
            myHolder.tvNidkName!!.text = model.nickName
            myHolder.tvContent!!.text = model.replyMessage
            if (model.releaseTime != null) {
                myHolder.tvPublicTime!!.text = model.releaseTime.toString()
            }
            myHolder.tvLikeCount!!.text = model.likeCount.toString()
        }
    }

    override fun getItemViewType(position: Int): Int {

        if (position == 0) {
            return HEADVIEW
        }

        if (data[position - 1] is SingTitleViewVo) {
            return SINGTITLE
        } else if (data[position - 1] is VideoCartVo) {
            return VIDEOCARD
        } else if (data[position - 1] is VideoCommentVo) {
            return VIDEOCOMMENT
        }

        return super.getItemViewType(position)
    }

    inner class SingTitleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView? = null

        init {
            tvTitle = itemView.findViewById(R.id.tv_title)
        }
    }


    inner class VideoCardHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView? = null
        var imCover: ImageView? = null
        var tvDesc: TextView? = null

        init {
            tvTitle = itemView.findViewById(R.id.tv_title)
            tvDesc = itemView.findViewById(R.id.tv_desc)
            imCover = itemView.findViewById(R.id.im_cover)
            imCover!!.setOnClickListener {
                if (onClick != null) {
                    onClick!!.onRecommendClick(imCover!!, adapterPosition - 1)
                }
            }
        }
    }

    inner class VideoCommentHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvAvatar: ImageView? = null
        var tvNidkName: TextView? = null
        var tvPublicTime: TextView? = null
        var tvContent: TextView? = null
        var tvLikeCount: TextView? = null

        init {
            tvAvatar = itemView.findViewById(R.id.im_avatar)
            tvNidkName = itemView.findViewById(R.id.tv_nickname)
            tvContent = itemView.findViewById(R.id.tv_content)
            tvPublicTime = itemView.findViewById(R.id.tv_public_time)
            tvLikeCount = itemView.findViewById(R.id.tv_like_count)
        }
    }

    inner class HeadViedoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvNidkName: TextView? = null
        var tvAuthorDesc: TextView? = null
        var tvCollectCount: TextView? = null
        var tvShareCount: TextView? = null
        var tvVideoDesc: TextView? = null
        var tvCategory: TextView? = null
        var tvTitle: TextView? = null
        var imAuthor: ImageView? = null

        init {
            tvNidkName = itemView.findViewById(R.id.tv_nickname)
            tvAuthorDesc = itemView.findViewById(R.id.tv_author_desc)
            tvCollectCount = itemView.findViewById(R.id.tv_collect_count)
            tvShareCount = itemView.findViewById(R.id.tv_share_count)
            tvVideoDesc = itemView.findViewById(R.id.tv_video_desc)
            tvCategory = itemView.findViewById(R.id.tv_category_name)
            tvTitle = itemView.findViewById(R.id.tv_title)
            imAuthor = itemView.findViewById(R.id.im_author)
        }
    }

    interface OnRecommendClickListener {
        fun onRecommendClick(view: View, position: Int)
    }
}