package com.huanting.openeye.ui.fragment.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.huanting.openeye.R
import com.huanting.openeye.ui.fragment.home.model.entity.vo.FollowCardVo
import com.huanting.openeye.ui.fragment.home.model.entity.vo.TitleViewVo
import com.huanting.openeye.utils.ImageUtils

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