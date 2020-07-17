package com.huanting.openeye.ui.fragment.community.adapter

import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.huanting.openeye.R
import com.huanting.openeye.ui.fragment.community.model.entity.recommend.ModelScrollcard
import com.huanting.openeye.utils.ImageUtils
import com.huanting.openeye.utils.Util

/**
 *Created by yicooll
 * on 2020/7/17
 */
class ScrollCardAdapter(layoutId:Int,data:List<ModelScrollcard.Data.Item>) :BaseQuickAdapter<ModelScrollcard.Data.Item,BaseViewHolder>(layoutId,data) {
    override fun convert(helper: BaseViewHolder?, item: ModelScrollcard.Data.Item?) {

        var cover=helper?.getView(R.id.im_cover) as ImageView
//        var container=helper?.getView<ConstraintLayout>(R.id.cl_card)
//        var lp : ConstraintLayout.LayoutParams?=null
//        if((helper!!.adapterPosition+1)%2!=0){
//            lp= ConstraintLayout.LayoutParams(
//                Util.getWindowMetrics(mContext!!)[0]/2- Util.dpTopx(20),
//                Util.dpTopx(40))
//            lp.setMargins(0,0, Util.dpTopx(5), Util.dpTopx(5))
//        }else{
//            lp= ConstraintLayout.LayoutParams(
//                Util.getWindowMetrics(mContext!!)[0]/2- Util.dpTopx(20),
//                Util.dpTopx(40))
//            lp.setMargins(Util.dpTopx(5),0,0, Util.dpTopx(5))
//        }
//        container?.layoutParams=lp
        ImageUtils.loadImage(cover,item?.data!!.bgPicture)
        helper!!.setText(R.id.tv_title,item?.data!!.title)
        helper!!.setText(R.id.tv_sub_titile,item?.data!!.subTitle)
    }
}