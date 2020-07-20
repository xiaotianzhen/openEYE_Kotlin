package com.huanting.openeye.ui.fragment.community.model.biz

import com.huanting.openeye.api_service.CommunityService
import com.huanting.openeye.network.RetrofitUtil
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *Created by yicooll
 * on 2020/7/17
 */
class IConcernBizImpl:IConcernBiz {
    override fun getConcernData(observer: Observer<Any>) {
        var cilent= RetrofitUtil.getRetorfit()
        var service=cilent?.create(CommunityService::class.java)
        service!!.getConcernData()
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }
}