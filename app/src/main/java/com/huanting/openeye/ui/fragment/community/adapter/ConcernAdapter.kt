package com.huanting.openeye.ui.fragment.community.adapter

import android.view.View
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.huanting.openeye.R
import com.huanting.openeye.ui.fragment.community.model.entity.vo.ConcernCardVo
import com.huanting.openeye.utils.ImageUtils
import com.huanting.openeye.utils.Util
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import kotlin.time.ExperimentalTime

/**
 *Created by yicooll
 * on 2020/7/20
 */
class ConcernAdapter(layoutId: Int, data: ArrayList<ConcernCardVo>) :
    BaseQuickAdapter<ConcernCardVo, BaseViewHolder>(layoutId, data) {

    companion object {
        var TAG = "CONCERN"
    }

    @ExperimentalTime
    override fun convert(helper: BaseViewHolder?, item: ConcernCardVo?) {
        ImageUtils.loadImageCircle(helper!!.getView(R.id.im_avatar), item!!.avatarUrl)
        helper!!.setText(R.id.tv_issuerName, item!!.issureName)
        helper!!.setText(R.id.tv_public_time, Util.formatData(item!!.releaseTime, "HH:mm") + "发布")
        helper!!.setText(R.id.tv_title, item!!.title)
        helper!!.setText(R.id.tv_desc, item!!.desc)
        helper!!.setText(R.id.tv_like, item!!.collectionCount.toString())
        helper!!.setText(R.id.tv_msg, item!!.replyCount.toString())


        var player = helper!!.getView<StandardGSYVideoPlayer>(R.id.gsy_player)
        //第一种模式
        player.setUpLazy(item!!.playUrl, true, null, null, item!!.title)
        //增加title
        player.titleTextView.visibility = View.GONE
        //设置返回键
        player.backButton.visibility = View.GONE
        //设置全屏按键功能
        player.fullscreenButton.setOnClickListener {
            player.startWindowFullscreen(mContext, false, true)
        }
        //防止错位设置
        player.playTag = TAG
        player.playPosition = helper!!.adapterPosition
        //是否根据视频尺寸，自动选择竖屏全屏或者横屏全屏
        player.isAutoFullWithSize = true
        //音频焦点冲突时是否释放
        player.isReleaseWhenLossAudio = false
        //全屏动画
        player.isShowFullAnimation = true
        //小屏时不触摸滑动
        player.setIsTouchWiget(false)

        //设置封面
        val imageView = ImageView(mContext)
        ImageUtils.loadImage(imageView, item!!.coverUrl)
        player.thumbImageView = imageView
    }
}