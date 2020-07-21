package com.huanting.openeye.ui.fragment.notify.view

import com.huanting.openeye.ui.fragment.notify.model.entity.notify.ModelMessage
import io.reactivex.Observer

/**
 *Created by yicooll
 * on 2020/7/20
 */
interface INotificationView {
    fun showNotificationView(model : ModelMessage)
}