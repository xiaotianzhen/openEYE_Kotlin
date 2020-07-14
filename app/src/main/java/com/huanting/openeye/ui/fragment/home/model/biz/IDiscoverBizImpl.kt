package com.huanting.openeye.ui.fragment.home.model.biz

import com.huanting.openeye.api_service.HomeService
import com.huanting.openeye.network.RetrofitUtil
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *Created by yicooll
 * on 2020/7/13
 */
class IDiscoverBizImpl:IDiscoverBiz {

    override fun getDiscoverData(observer: Observer<Any>) {
        var cilent=RetrofitUtil.getRetorfit()
        var service=cilent?.create(HomeService::class.java)
        service!!.getDiscoverData()
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }
}