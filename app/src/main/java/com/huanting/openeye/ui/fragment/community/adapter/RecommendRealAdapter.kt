package com.huanting.openeye.ui.fragment.community.adapter

import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.huanting.openeye.R
import com.huanting.openeye.ui.fragment.community.model.entity.vo.RecommendCardVo
import com.huanting.openeye.utils.ImageUtils
import com.huanting.openeye.utils.Util

/**
 *Created by yicooll
 * on 2020/7/17
 */
class RecommendRealAdapter(layoutId:Int,data:ArrayList<RecommendCardVo>):BaseQuickAdapter<RecommendCardVo,BaseViewHolder>(layoutId,data) {
    override fun convert(helper: BaseViewHolder?, item: RecommendCardVo?) {

        var imCover=helper!!.getView(R.id.im_cover) as ImageView
        var imHeight=item!!.imgHeight*(Util.getWindowMetrics(mContext)[0]/2-12)/item.imgWidth
        var lp= ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,imHeight.toInt())
        imCover.layoutParams=lp
        ImageUtils.loadImage(imCover,item!!.cover)
        helper!!.setText(R.id.tv_desc,item!!.desc)
        helper!!.setText(R.id.tv_nickname,item!!.nickName)
        helper!!.setText(R.id.tv_collect_count,item!!.collectionCount.toString())
        ImageUtils.loadImageCircle(helper!!.getView(R.id.im_avatar) as ImageView,item!!.avatarUrl)
    }
}