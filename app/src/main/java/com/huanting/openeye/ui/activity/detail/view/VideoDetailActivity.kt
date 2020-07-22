package com.huanting.openeye.ui.activity.detail.view

import androidx.recyclerview.widget.LinearLayoutManager
import com.huanting.openeye.R
import com.huanting.openeye.base.BaseActivity
import com.huanting.openeye.ui.activity.detail.adapter.VideoDetailAdapter
import com.huanting.openeye.ui.activity.detail.model.vo.VideoDetailVo
import com.huanting.openeye.ui.activity.detail.presenter.VideoDetailPresenter
import com.huanting.openeye.utils.ImageUtils
import kotlinx.android.synthetic.main.activity_video_detail.*

class VideoDetailActivity : BaseActivity(), IVideoDetailView {

    private var presenter: VideoDetailPresenter? = null
    private var adapter: VideoDetailAdapter? = null
    private var data = ArrayList<Any>()
    private var videoModel: VideoDetailVo? = null
    override fun getContentViewLayoutId(): Int {
        return R.layout.activity_video_detail
    }

    override fun initView() {
        presenter = VideoDetailPresenter(this)
        var intent = intent
        videoModel = intent.getSerializableExtra("detail") as VideoDetailVo
        adapter = VideoDetailAdapter(this, data, videoModel!!)
        rv_detail.adapter = adapter
        rv_detail.layoutManager = LinearLayoutManager(this)
        ImageUtils.loadImage(im_bg,videoModel!!.blurredUrl)
    }

    override fun initEvent() {

        presenter?.getDetailData(videoModel!!.videoId.toString())
    }

    override fun showVideoDetailView(model: ArrayList<Any>) {
        data.clear()
        data.addAll(model)
        adapter?.notifyDataSetChanged()
    }


}