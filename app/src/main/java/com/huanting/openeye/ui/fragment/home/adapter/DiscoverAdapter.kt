package com.huanting.openeye.ui.fragment.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.huanting.openeye.R
import com.huanting.openeye.ui.fragment.home.model.entity.discover.*
import kotlin.collections.ArrayList


/**
 *Created by yicooll
 * on 2020/7/14
 */
class DiscoverAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private val CATEGOROY_CART=1
    private val SUBJECT_CART=2
    private val TEXTVIEW=3
    private val BANNER=4
    private val VIDEOSMALLCARD=5
    private val BRIEFCARD=6

    private var data:ArrayList<Any>?=null
    private var mContext:Context?=null

    constructor(context: Context, data:ArrayList<Any>){
        mContext=context
        this.data=data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            CATEGOROY_CART -> {
                return BriefcardViewHolder(
                    LayoutInflater.from(mContext).inflate(R.layout.adapter_brief_layout,parent,false)
                )
            }
            SUBJECT_CART -> {
                return BriefcardViewHolder(
                    LayoutInflater.from(mContext).inflate(R.layout.adapter_brief_layout,parent,false)
                )
            }
            TEXTVIEW -> {
                return BriefcardViewHolder(
                    LayoutInflater.from(mContext).inflate(R.layout.adapter_brief_layout,parent,false)
                )
            }
            BANNER -> {
                return BriefcardViewHolder(
                    LayoutInflater.from(mContext).inflate(R.layout.adapter_brief_layout,parent,false)
                )
            }
            VIDEOSMALLCARD -> {
                return BriefcardViewHolder(
                    LayoutInflater.from(mContext).inflate(R.layout.adapter_brief_layout,parent,false)
                )
            }
            BRIEFCARD -> {
                return BriefcardViewHolder(
                    LayoutInflater.from(mContext).inflate(R.layout.adapter_brief_layout,parent,false)
                )
            }

        }
        return BriefcardViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.adapter_brief_layout,parent,false)
        )

    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(data!![position] is ModelCategoryCart){

        }else if(data!![position] is ModelSubjectCard ){

        }else if(data!![position] is ModelTitleView ){

        }else if(data!![position] is ModelBanner ){

        }else if(data!![position] is ModelVideoSmallCard ){

        }else if(data!![position] is ModelBriefcard ){
           var myHolder:BriefcardViewHolder= holder as BriefcardViewHolder
            var briefcard:ModelBriefcard=data!![position] as ModelBriefcard
            myHolder.tv_title!!.text=briefcard.data.title
        }
    }



    override fun getItemViewType(position: Int): Int {

        if(data!![position] is ModelCategoryCart){
            return CATEGOROY_CART
        }else if(data!![position] is ModelSubjectCard ){
            return SUBJECT_CART
        }else if(data!![position] is ModelTitleView ){
            return TEXTVIEW
        }else if(data!![position] is ModelBanner ){
            return BANNER
        }else if(data!![position] is ModelVideoSmallCard){
            return VIDEOSMALLCARD
        }else if(data!![position] is ModelBriefcard){
            return BRIEFCARD
        }
        return super.getItemViewType(position)
    }

    inner class  CategoryCartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    inner class  SubjectCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
    inner class  TitleViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
    inner class  BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
    inner class  VideoSmallCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
    inner class  BriefcardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_title:TextView?=null

        init {
            tv_title=itemView.findViewById(R.id.tv_title)
        }
    }


}