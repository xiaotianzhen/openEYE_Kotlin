package com.huanting.openeye.ui.fragment.community.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.huanting.openeye.R
import com.huanting.openeye.ui.fragment.community.model.entity.recommend.ModelScrollcard
import com.huanting.openeye.ui.fragment.community.model.entity.vo.RecommendCardVo
import com.huanting.openeye.ui.widget.RecyclerDividerItemDecoration
import com.huanting.openeye.ui.widget.StaggeredDividerItemDecoration
import com.huanting.openeye.utils.Util

/**
 *Created by yicooll
 * on 2020/7/17
 */

class RecommendAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private var mContext:Context?=null
    private var data=ArrayList<Any>()

    private val SCROLLCARD=0
    private val RECOMMENDCARD=1
    constructor(context:Context,data:ArrayList<Any>){
        this.mContext=context
        this.data=data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
               when(viewType){
                   SCROLLCARD->{
                      return ScrollCardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_recycler_child,parent,false))
                   }
                   RECOMMENDCARD->{
                       return RecommendCardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_recycler_child,parent,false))
                   }
               }
        return   return ScrollCardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_recycler_child,parent,false))
    }

    override fun getItemCount(): Int {
       return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(data[position] is ModelScrollcard){

            var myHolder=holder as ScrollCardViewHolder
            var model =data[position] as ModelScrollcard
            myHolder.rvChild!!.adapter=ScrollCardAdapter(R.layout.adapter_scroll_cart,model.data.itemList)
            myHolder.rvChild!!.layoutManager=GridLayoutManager(mContext!!,2,LinearLayoutManager.VERTICAL,false)
            myHolder.rvChild!!.addItemDecoration(
                RecyclerDividerItemDecoration(
                    mContext,
                    Util.dpTopx(10)
                )
            )

        }else if(data[position] is ArrayList<*>){
            var model=data[position] as ArrayList<Any>
            if(model.size>0&&model[0] is RecommendCardVo){
                var myHolder=holder as RecommendCardViewHolder
                var model =data[position] as  ArrayList<RecommendCardVo>
                myHolder.rvChild!!.adapter=RecommendRealAdapter(R.layout.adapter_recommend_card,model)
                myHolder.rvChild!!.layoutManager=
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                myHolder.rvChild!!.addItemDecoration(
                    StaggeredDividerItemDecoration(
                        mContext,
                        Util.dpTopx(10)
                    )
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(data[position] is ModelScrollcard){
            return SCROLLCARD
        }else if(data[position] is ArrayList<*>){
            var model=data[position] as ArrayList<Any>
            if(model.size>0&&model[0] is RecommendCardVo){
                return RECOMMENDCARD
            }
        }

        return super.getItemViewType(position)
    }
   inner class ScrollCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

       var rvChild:RecyclerView?=null
       init {
           rvChild=itemView.findViewById(R.id.rv_child)
       }
   }

    inner class RecommendCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var rvChild:RecyclerView?=null
        init {
            rvChild=itemView.findViewById(R.id.rv_child)
        }
    }
}