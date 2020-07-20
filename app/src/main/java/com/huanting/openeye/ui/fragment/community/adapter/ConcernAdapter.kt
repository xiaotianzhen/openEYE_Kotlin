package com.huanting.openeye.ui.fragment.community.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.huanting.openeye.R
import com.huanting.openeye.ui.fragment.community.model.entity.vo.ConcernCardVo
import com.huanting.openeye.utils.ImageUtils
import com.huanting.openeye.utils.Util
import kotlin.time.ExperimentalTime

/**
 *Created by yicooll
 * on 2020/7/20
 */
class ConcernAdapter(layoutId:Int,data:ArrayList<ConcernCardVo>):BaseQuickAdapter<ConcernCardVo,BaseViewHolder>(layoutId,data) {

    @ExperimentalTime
    override fun convert(helper: BaseViewHolder?, item: ConcernCardVo?) {
         ImageUtils.loadImageCircle(helper!!.getView(R.id.im_avatar),item!!.avatarUrl)
         helper!!.setText(R.id.tv_issuerName,item!!.issureName)
            helper!!.setText(R.id.tv_public_time,Util.formatData(item!!.releaseTime,"HH:mm")+"发布")
        helper!!.setText(R.id.tv_title,item!!.title)
        helper!!.setText(R.id.tv_desc,item!!.desc)
        helper!!.setText(R.id.tv_like,item!!.collectionCount.toString())
        helper!!.setText(R.id.tv_msg,item!!.replyCount.toString())
    }
}