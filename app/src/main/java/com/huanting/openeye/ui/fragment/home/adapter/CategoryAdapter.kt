package com.huanting.openeye.ui.fragment.home.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.huanting.openeye.R
import com.huanting.openeye.ui.fragment.home.model.entity.discover.ModelCategoryCart


/**
 *Created by yicooll
 * on 2020/7/15
 */
class CategoryAdapter(layoutId:Int,date:List<ModelCategoryCart.Data.Item>):BaseQuickAdapter<ModelCategoryCart.Data.Item,BaseViewHolder> (layoutId,date){


    override fun convert(helper: BaseViewHolder?, item: ModelCategoryCart.Data.Item?) {
         helper?.setText(R.id.tv_category_name,item?.data?.title)
         Glide.with(mContext).load(item?.data?.image).into(helper?.getView(R.id.im_cover) as ImageView)
    }
}