package com.huanting.openeye.ui.fragment.notify.view

import com.huanting.openeye.ui.fragment.notify.model.entity.vo.TopicViewVo

/**
 *Created by yicooll
 * on 2020/7/20
 */
interface ITopicChildView {
    fun showTopicChildView(model:ArrayList<TopicViewVo>)
    fun setNextPageUrl(pageUrl:String)
}