package com.huanting.openeye.ui.fragment.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bigkoo.convenientbanner.ConvenientBanner
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator
import com.bigkoo.convenientbanner.holder.Holder
import com.bumptech.glide.Glide
import com.huanting.openeye.Constant
import com.huanting.openeye.R
import com.huanting.openeye.ui.fragment.home.model.entity.discover.*
import kotlin.collections.ArrayList


/**
 *Created by yicooll
 * on 2020/7/14
 */
class DiscoverAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private val CATEGOROY_CART=1
    private val SUBJECT_CART=2
    private val TEXTVIEW=3
    private val BANNER=4
    private val VIDEOSMALLCARD=5
    private val BRIEFCARD=6
    private val SCROLL_BANNER=7

    private var data:ArrayList<Any>?=null
    private var mContext:Context?=null
    private var mImageLoadHoder: BannerHolder? = null
    constructor(context: Context, data:ArrayList<Any>){
        mContext=context
        this.data=data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            CATEGOROY_CART -> {
                return CategoryCartViewHolder(
                    LayoutInflater.from(mContext).inflate(R.layout.adapter_discover_category,parent,false)
                )
            }
            SUBJECT_CART -> {
                return SubjectCardViewHolder(
                    LayoutInflater.from(mContext).inflate(R.layout.adapter_discover_subject,parent,false)
                )
            }
            TEXTVIEW -> {
                return TitleViewViewHolder(
                    LayoutInflater.from(mContext).inflate(R.layout.adapter_title,parent,false)
                )
            }
            BANNER -> {
                return BannerViewHolder(
                    LayoutInflater.from(mContext).inflate(R.layout.adatper_discover_banner,parent,false)
                )
            }
            VIDEOSMALLCARD -> {
                return VideoSmallCardViewHolder(
                    LayoutInflater.from(mContext).inflate(R.layout.adapter_small_video,parent,false)
                )
            }
            BRIEFCARD -> {
                return BriefcardViewHolder(
                    LayoutInflater.from(mContext).inflate(R.layout.adapter_brief_layout,parent,false)
                )
            }
            SCROLL_BANNER->{

                return ScrollBannerViewHolder(
                    LayoutInflater.from(mContext).inflate(R.layout.adapter_scroll_banner,parent,false)
                )
            }

        }
        return BriefcardViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.adapter_brief_layout,parent,false)
        )

    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(data!![position] is ModelCategoryCart){
            var myHolder=holder as CategoryCartViewHolder
            var model=data!![position] as ModelCategoryCart
            myHolder.tvTitle?.text=model.data.header.title
            myHolder.tvMore?.text=model.data.header.rightText
            myHolder.rvCategory?.adapter=CategoryAdapter(R.layout.adapter_category,model.data.itemList)
            myHolder.rvCategory?.layoutManager=GridLayoutManager(mContext!!,2,GridLayoutManager.HORIZONTAL,false)
        }else if(data!![position] is ModelSubjectCard ){
            var myHolder=holder as SubjectCardViewHolder
            var model=data!![position] as ModelSubjectCard
            myHolder.tvTitle?.text=model.data.header.title
            myHolder.tvMore?.text=model.data.header.rightText
            myHolder.rvSubject?.adapter=SubjectAdapter(R.layout.adapter_subject_cart,model.data.itemList)
            myHolder.rvSubject?.layoutManager=GridLayoutManager(mContext!!,2,GridLayoutManager.VERTICAL,false)
        }else if(data!![position] is ModelTitleView ){
            var myHolder:TitleViewViewHolder= holder as TitleViewViewHolder
            var modelTitle=data!![position] as ModelTitleView
            myHolder.tvTitle?.text=modelTitle.data.text
            myHolder.tvMore?.text=modelTitle.data.rightText+" >"
        }else if(data!![position] is ModelBanner ){
            var myHolder:BannerViewHolder= holder as BannerViewHolder
            var model:ModelBanner=data!![position] as ModelBanner
            Glide.with(mContext!!).load(model.data.image).into(myHolder.imBanner!!)
        }else if(data!![position] is ModelVideoSmallCard ){
            var myHolder:VideoSmallCardViewHolder= holder as VideoSmallCardViewHolder
            var model:ModelVideoSmallCard=data!![position] as ModelVideoSmallCard
            myHolder.tvTitle?.text=model.data.title
            myHolder.tvDesc?.text=model.data.description
            Glide.with(mContext!!).load(model.data.cover.detail).into(myHolder.imCover!!)
        }else if(data!![position] is ModelBriefcard ){
           var myHolder:BriefcardViewHolder= holder as BriefcardViewHolder
            var briefcard:ModelBriefcard=data!![position] as ModelBriefcard
            myHolder.tvTitle?.text=briefcard.data.title
            myHolder.tvDesc?.text=briefcard.data.description
            Glide.with(mContext!!).load(briefcard.data.icon).into(myHolder.imCover!!)
        }else if(data!![position] is ModelTopBanner ){
            var myHolder= holder as ScrollBannerViewHolder
            var model=data!![position] as ModelTopBanner
            myHolder.cbBanner!!.setPages(CBViewHolderCreator<BannerHolder> {
                if (mImageLoadHoder == null) {
                    mImageLoadHoder = BannerHolder()
                }
                return@CBViewHolderCreator mImageLoadHoder
            }, model.data.itemList as List<Nothing>).setPageIndicator(intArrayOf(R.mipmap.ic_indicator_normal, R.mipmap.ic_indicator_selected))
            .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .startTurning(Constant.BANNER_TURN)

        }
    }


    inner class BannerHolder : Holder<ModelTopBanner.Data.Item> {

        private var imageView: ImageView? = null
        override fun UpdateUI(context: Context?, position: Int, data: ModelTopBanner.Data.Item?) {
            Glide.with(context!!).load(data!!.data.image).into(imageView!!)
        }

        override fun createView(context: Context?): View? {
            imageView = ImageView(context)
            imageView!!.scaleType = ImageView.ScaleType.CENTER_CROP
            return imageView
        }

    }


    override fun getItemViewType(position: Int): Int {

        if(data!![position] is ModelCategoryCart){
            return CATEGOROY_CART
        }else if(data!![position] is ModelSubjectCard ){
            return SUBJECT_CART
        }else if(data!![position] is ModelTitleView ){
            return TEXTVIEW
        }else if(data!![position] is ModelBanner ){
            return BANNER
        }else if(data!![position] is ModelVideoSmallCard){
            return VIDEOSMALLCARD
        }else if(data!![position] is ModelBriefcard){
            return BRIEFCARD
        }else if(data!![position] is ModelTopBanner){
            return SCROLL_BANNER
        }
        return super.getItemViewType(position)
    }

    inner class  CategoryCartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var rvCategory:RecyclerView?=null
        var tvTitle:TextView?=null
        var tvMore:TextView?=null
        init {
            tvTitle=itemView.findViewById(R.id.tv_title)
            tvMore=itemView.findViewById(R.id.tv_more)
            rvCategory=itemView.findViewById(R.id.rv_category)
        }
    }

    inner class  SubjectCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var rvSubject:RecyclerView?=null
        var tvTitle:TextView?=null
        var tvMore:TextView?=null
        init {
            tvTitle=itemView.findViewById(R.id.tv_title)
            tvMore=itemView.findViewById(R.id.tv_more)
            rvSubject=itemView.findViewById(R.id.rv_subject)
        }
    }
    inner class  TitleViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle:TextView?=null
        var tvMore:TextView?=null
        init {
            tvTitle=itemView.findViewById(R.id.tv_title)
            tvMore=itemView.findViewById(R.id.tv_more)
        }
    }
    inner class  BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       var imBanner:ImageView?=null
        init {
            imBanner=itemView.findViewById(R.id.im_banner)
        }
    }
    inner class  VideoSmallCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle:TextView?=null
        var imCover:ImageView?=null
        var tvDesc:TextView?=null
        init {
            tvTitle=itemView.findViewById(R.id.tv_title)
            tvDesc=itemView.findViewById(R.id.tv_desc)
            imCover=itemView.findViewById(R.id.im_cover)
        }
    }
    inner class  BriefcardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle:TextView?=null
        var imCover:ImageView?=null
        var tvDesc:TextView?=null
        init {
            tvTitle=itemView.findViewById(R.id.tv_title)
            tvDesc=itemView.findViewById(R.id.tv_desc)
            imCover=itemView.findViewById(R.id.im_cover)
        }
    }

    inner class  ScrollBannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cbBanner:ConvenientBanner<ModelTopBanner.Data.Item>?=null
        init {
            cbBanner=itemView.findViewById(R.id.cb_banner)
        }
    }
}