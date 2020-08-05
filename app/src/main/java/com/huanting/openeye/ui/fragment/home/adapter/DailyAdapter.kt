package com.huanting.openeye.ui.fragment.home.adapter

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.huanting.openeye.R
import com.huanting.openeye.ui.activity.detail.model.vo.VideoDetailVo
import com.huanting.openeye.ui.activity.detail.view.VideoDetailActivity
import com.huanting.openeye.ui.fragment.home.model.entity.vo.FollowCardVo
import com.huanting.openeye.ui.fragment.home.model.entity.vo.TitleViewVo
import com.huanting.openeye.utils.ImageUtils
import com.huanting.openeye.utils.ToActivityHelper

/**
 *Created by yicooll
 * on 2020/7/16
 */
class DailyAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private var mContext:Context?=null
    private var data=ArrayList<Any>()
    private val TITLEVIEW=0
    private val FOLLOWCARD=1

    constructor(context: Context,model:ArrayList<Any>){
        mContext=context
        data=model
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
         when(viewType){
             TITLEVIEW->{
                 return  TitleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_title,parent,false))
             }
             FOLLOWCARD->{
                 return  FollowcardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_follow_card,parent,false))
             }
         }
        return  TitleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_title,parent,false))
    }

    override fun getItemCount(): Int {

        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(data[position] is TitleViewVo){
            var myHolder=holder as TitleViewHolder
            var model= data[position] as TitleViewVo
            myHolder.tvTitle?.text=model.title
            myHolder.tvMore?.visibility=View.GONE
        }else if(data[position] is FollowCardVo){
            var myHolder=holder as FollowcardViewHolder
            var model= data[position] as FollowCardVo
            if(model.authorUrl!=null)
            ImageUtils.loadImageCircle(myHolder.imAuthor!!,model.authorUrl.toString())
            ImageUtils.loadImage(myHolder.imCover!!,model.coverUrl)
            myHolder.tvTitle?.text=model.title
            myHolder.tvDesc?.text=model.desc
        }

    }

    override fun getItemViewType(position: Int): Int {

        if(data[position] is TitleViewVo){
            return TITLEVIEW
        }else if(data[position] is FollowCardVo){
            return FOLLOWCARD
        }
        return super.getItemViewType(position)
    }


    inner class FollowcardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView?=null
        var tvDesc: TextView?=null
        var imCover: ImageView?=null
        var imAuthor: ImageView?=null
        init {
            tvTitle=itemView.findViewById(R.id.tv_title)
            tvDesc=itemView.findViewById(R.id.tv_desc)
            imCover=itemView.findViewById(R.id.im_cover)
            imAuthor=itemView.findViewById(R.id.im_author)
            imCover!!.setOnClickListener {
                var bundle = Bundle()
                var model = data[adapterPosition] as FollowCardVo
                bundle.putSerializable(
                    "detail", VideoDetailVo(
                        model.coverUrl,
                        model.videoTime.toLong(),
                        model.title,
                        model.desc,
                        model.authorUrl!!,
                        model.videoDesc,
                        model.userDesc,
                        model.nickName,
                        model.playerUrl,
                        model.blurredUrl,
                        "",
                        model.videoId,
                        0,
                        0
                    )
                )
                ToActivityHelper.getInstance()!!.toActivity(
                    mContext as Activity,
                    VideoDetailActivity::class.java, bundle
                )
            }
        }
    }
    inner class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView?=null
        var tvMore: TextView?=null
        init {
            tvTitle=itemView.findViewById(R.id.tv_title)
            tvMore=itemView.findViewById(R.id.tv_more)
        }
    }
}