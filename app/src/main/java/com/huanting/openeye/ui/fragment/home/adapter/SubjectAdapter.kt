package com.huanting.openeye.ui.fragment.home.adapter


import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.huanting.openeye.R
import com.huanting.openeye.ui.fragment.home.model.entity.discover.ModelSubjectCard
import com.huanting.openeye.utils.ImageUtils
import com.huanting.openeye.utils.Util


/**
 *Created by yicooll
 * on 2020/7/15
 */
class SubjectAdapter(layoutId:Int, date:List<ModelSubjectCard.Data.Item>):BaseQuickAdapter<ModelSubjectCard.Data.Item,BaseViewHolder> (layoutId,date){


    override fun convert(helper: BaseViewHolder?, item: ModelSubjectCard.Data.Item?) {
        helper?.setText(R.id.tv_category_name,item?.data?.title)
        var cover=helper?.getView(R.id.im_cover) as ImageView
        var container=helper?.getView<ConstraintLayout>(R.id.cl_subject)
        var lp :ConstraintLayout.LayoutParams?=null
        if((helper!!.adapterPosition+1)%2!=0){
            lp=ConstraintLayout.LayoutParams(Util.getWindowMetrics(mContext)[0]/2-Util.dpTopx(10),Util.dpTopx(60))
            lp.setMargins(0,0,Util.dpTopx(10),Util.dpTopx(5))
        }else{
            lp=ConstraintLayout.LayoutParams(Util.getWindowMetrics(mContext)[0]/2-Util.dpTopx(10),Util.dpTopx(60))
            lp.setMargins(Util.dpTopx(10),0,0,Util.dpTopx(5))
        }
        container.layoutParams=lp
        ImageUtils.loadImage(cover,item?.data!!.image)

    }
}