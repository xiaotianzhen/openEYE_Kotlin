package com.huanting.openeye.ui.fragment.notify.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.huanting.openeye.R
import com.huanting.openeye.ui.fragment.notify.model.entity.notify.ModelMessage

/**
 *Created by yicooll
 * on 2020/7/20
 */
class MessageAdapter(layoutId:Int,data:List<ModelMessage.Message>):BaseQuickAdapter<ModelMessage.Message,BaseViewHolder>(layoutId,data)  {
    override fun convert(helper: BaseViewHolder?, item: ModelMessage.Message?) {
        helper!!.setText(R.id.tv_title,item!!.title)
        helper!!.setText(R.id.tv_desc,item!!.content)
        helper!!.setText(R.id.tv_public_time,getTime(item.date))
    }


    fun getTime(time:Long):String{

        var currentTime=System.currentTimeMillis()
        var dtTime=currentTime-time

        if(dtTime/1000/((3600*24)*365)>0){
            return (dtTime/1000/((3600*24)*365)).toString()+"年"
        }
        if(dtTime/1000/(3600*24)>0){
            return (dtTime/1000/(3600*24)).toString()+"天"
        }
        if(dtTime/1000%(3600*24)/3600>0){
            return (dtTime/1000%(3600*24)/3600).toString()+"小时"
        }
        return "刚刚"
    }
}