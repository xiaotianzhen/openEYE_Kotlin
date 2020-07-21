package com.huanting.openeye.ui.fragment.notify.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.huanting.openeye.R
import com.huanting.openeye.ui.fragment.notify.model.entity.vo.TopicViewVo
import com.huanting.openeye.utils.ImageUtils

/**
 *Created by yicooll
 * on 2020/7/20
 */
class TopicAdapter(layoutId:Int,data:ArrayList<TopicViewVo>):BaseQuickAdapter<TopicViewVo,BaseViewHolder>(layoutId,data) {
    override fun convert(helper: BaseViewHolder?, item: TopicViewVo?) {
        ImageUtils.loadImage(helper!!.getView(R.id.im_cover),item!!.coverUrl)
        helper!!.setText(R.id.tv_title,item!!.title)
        if(item!!.desc!=null)
        helper!!.setText(R.id.tv_desc,item!!.desc)
    }
}