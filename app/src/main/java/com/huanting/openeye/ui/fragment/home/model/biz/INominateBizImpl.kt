package com.huanting.openeye.ui.fragment.home.model.biz

import com.huanting.openeye.api_service.HomeService
import com.huanting.openeye.network.RetrofitUtil
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *Created by yicooll
 * on 2020/7/15
 */
class INominateBizImpl:INominateBiz {

    override fun getNominateData(url:String,observer: Observer<Any>) {
        var cilent= RetrofitUtil.getRetorfit()
        var service=cilent?.create(HomeService::class.java)
        service!!.getNominateData(url)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }
}