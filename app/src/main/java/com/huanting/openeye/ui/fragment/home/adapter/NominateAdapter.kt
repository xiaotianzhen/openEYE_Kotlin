package com.huanting.openeye.ui.fragment.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.huanting.openeye.R
import com.huanting.openeye.ui.fragment.home.model.entity.vo.FollowCardVo
import com.huanting.openeye.ui.fragment.home.model.entity.vo.SingTitleViewVo
import com.huanting.openeye.ui.fragment.home.model.entity.vo.TitleViewVo
import com.huanting.openeye.ui.fragment.home.model.entity.vo.VideoCartVo
import com.huanting.openeye.utils.ImageUtils

/**
 *Created by yicooll
 * on 2020/7/16
 */
class NominateAdapter :RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private var data=ArrayList<Any>()
    private var mContext:Context?=null

    private val FOLLOWCARD=0
    private val SINGTITLEVIEW=1
    private val TITLEVIEW=2
    private val VIDEOCARD=3

    constructor(context: Context, data:ArrayList<Any>){
        mContext=context
        this.data=data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
               when(viewType){
                   FOLLOWCARD->{
                       return  FollowcardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_follow_card,parent,false))
                   }
                   SINGTITLEVIEW->{
                       return  TitleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_title,parent,false))
                   }
                   TITLEVIEW->{
                       return  TitleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_title,parent,false))
                   }
                   VIDEOCARD->{
                       return  VideoCardHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_small_video,parent,false))
                   }
               }
        return  FollowcardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_small_video,parent,false))
    }

    override fun getItemCount(): Int {
       return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(data[position] is FollowCardVo){
            var myHolder=holder as FollowcardViewHolder
            var model= data[position] as FollowCardVo
            if(model.authorUrl!=null)
            ImageUtils.loadImageCircle(myHolder.imAuthor!!,model.authorUrl.toString())
            ImageUtils.loadImage(myHolder.imCover!!,model.coverUrl)
            myHolder.tvTitle?.text=model.title
            myHolder.tvDesc?.text=model.desc
        }else if(data[position] is SingTitleViewVo){
            var myHolder=holder as TitleViewHolder
            var model= data[position] as SingTitleViewVo
            myHolder.tvTitle?.text=model.title
            myHolder.tvMore?.visibility=View.GONE
        }else if(data[position] is TitleViewVo){
            var myHolder=holder as TitleViewHolder
            var model= data[position] as TitleViewVo
            myHolder.tvTitle?.text=model.title
            myHolder.tvMore?.text=model.actionTitle
        }else if(data[position] is VideoCartVo){
            var myHolder= holder as VideoCardHolder
            var model: VideoCartVo =data[position] as VideoCartVo
            myHolder.tvTitle?.text=model.title
            myHolder.tvDesc?.text=model.desc
            Glide.with(mContext!!).load(model.cover).into(myHolder.imCover!!)
        }
    }

    override fun getItemViewType(position: Int): Int {

        if(data[position] is FollowCardVo){
           return FOLLOWCARD
        }else if(data[position] is SingTitleViewVo){
            return SINGTITLEVIEW
        }else if(data[position] is TitleViewVo){
            return TITLEVIEW
        }else if(data[position] is VideoCartVo){
            return VIDEOCARD
        }
        return super.getItemViewType(position)
    }

    inner class FollowcardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle:TextView?=null
        var tvDesc:TextView?=null
        var imCover:ImageView?=null
        var imAuthor:ImageView?=null
        init {
            tvTitle=itemView.findViewById(R.id.tv_title)
            tvDesc=itemView.findViewById(R.id.tv_desc)
            imCover=itemView.findViewById(R.id.im_cover)
            imAuthor=itemView.findViewById(R.id.im_author)
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

    inner class VideoCardHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle:TextView?=null
        var imCover: ImageView?=null
        var tvDesc:TextView?=null
        init {
            tvTitle=itemView.findViewById(R.id.tv_title)
            tvDesc=itemView.findViewById(R.id.tv_desc)
            imCover=itemView.findViewById(R.id.im_cover)
        }
    }

}