package com.huanting.openeye.ui.activity.detail.view

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.huanting.openeye.R
import com.huanting.openeye.base.BaseVideoActivity
import com.huanting.openeye.ui.activity.detail.adapter.VideoDetailAdapter
import com.huanting.openeye.ui.activity.detail.model.vo.VideoDetailVo
import com.huanting.openeye.ui.activity.detail.presenter.VideoDetailPresenter
import com.huanting.openeye.ui.fragment.home.model.entity.vo.VideoCartVo
import com.huanting.openeye.utils.ImageUtils
import com.huanting.openeye.utils.ToActivityHelper
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import kotlinx.android.synthetic.main.activity_video_detail.*

class VideoDetailActivity : BaseVideoActivity(), IVideoDetailView {

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
        ImageUtils.loadImage(im_bg, videoModel!!.blurredUrl)

        //增加title
        gsy_detail_player.titleTextView.text = videoModel!!.title
        gsy_detail_player.backButton.visibility = View.VISIBLE
        initVideoBuilderMode();
    }

    override fun initEvent() {

        presenter?.getDetailData(videoModel!!.videoId.toString())
        gsy_detail_player.backButton.setOnClickListener {
            finish()
        }
        adapter?.setOnRecommendClickListener(object : VideoDetailAdapter.OnRecommendClickListener {
            override fun onRecommendClick(view: View, position: Int) {
                var model = data[position] as VideoCartVo
                videoModel = VideoDetailVo(
                    model.cover,
                    model.vidoTime.toLong(),
                    model.title,
                    model.desc,
                    model.authorUrl!!,
                    model.userDesc,
                    model.nickName,
                    "",
                    model.playerUrl,
                    model.blurredUrl,
                    "",
                    model.videoId,
                    model.collectionCount,
                    model.shareCount
                )
                adapter?.updataHead(videoModel!!)
                presenter?.getDetailData(videoModel!!.videoId.toString())
                initVideoBuilderMode();
            }
        })
    }


    override fun showVideoDetailView(model: ArrayList<Any>) {
        data.clear()
        data.addAll(model)
        adapter?.notifyDataSetChanged()
    }

    override fun clickForFullScreen() {
    }

    override fun getDetailOrientationRotateAuto(): Boolean {
        return true
    }

    override fun getGSYVideoPlayer(): StandardGSYVideoPlayer {
        return gsy_detail_player
    }

    override fun getGSYVideoOptionBuilder(): GSYVideoOptionBuilder {
        //内置封面可参考SampleCoverVideo
        val imageView = ImageView(this)
        ImageUtils.loadImage(imageView, videoModel!!.coverUrl)
        return GSYVideoOptionBuilder()
            .setThumbImageView(imageView)
            .setUrl(videoModel!!.palyerUrl)
            .setCacheWithPlay(true)
            .setVideoTitle(" ")
            .setIsTouchWiget(true)
            .setRotateViewAuto(false)
            .setLockLand(false)
            .setShowFullAnimation(false)
            .setNeedLockFull(true)
            .setSeekRatio(1f)
    }
}