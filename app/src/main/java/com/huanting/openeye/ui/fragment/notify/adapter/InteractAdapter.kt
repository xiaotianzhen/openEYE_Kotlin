package com.huanting.openeye.ui.fragment.notify.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.huanting.openeye.R
import com.huanting.openeye.ui.fragment.notify.model.entity.vo.InteractVo
import com.huanting.openeye.utils.ImageUtils
import kotlinx.android.synthetic.main.adapter_interact.view.*

/**
 *Created by yicooll
 * on 2020/7/21
 */
class InteractAdapter(layoutId:Int,data:ArrayList<InteractVo>):BaseQuickAdapter<InteractVo,BaseViewHolder>(layoutId,data) {

    override fun convert(helper: BaseViewHolder?, item: InteractVo?) {
        ImageUtils.loadImage(helper!!.getView(R.id.im_cover),item!!.cover)
        helper!!.setText(R.id.tv_title,item!!.title)
        helper!!.setText(R.id.tv_throught,item!!.viewNum.toString()+"人浏览/")
        helper!!.setText(R.id.tv_join,item!!.joinNum.toString()+"人参与")
    }
}