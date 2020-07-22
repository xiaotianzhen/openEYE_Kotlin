package com.huanting.openeye.ui.fragment.community.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.huanting.openeye.R
import com.huanting.openeye.base.BaseFragment
import com.huanting.openeye.ui.fragment.community.adapter.ConcernAdapter
import com.huanting.openeye.ui.fragment.community.model.entity.vo.ConcernCardVo
import com.huanting.openeye.ui.fragment.community.presenter.ConcernPresenter
import com.shuyu.gsyvideoplayer.GSYVideoManager
import kotlinx.android.synthetic.main.fragment_concern.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ConcernFragment : BaseFragment(),IConcernView {

    private var param1: String? = null
    private var param2: String? = null

    private var myAdapter: ConcernAdapter?=null

    private var presenter: ConcernPresenter?=null

    private var data = ArrayList<ConcernCardVo>()

    override fun initView() {
        presenter= ConcernPresenter(this)
        myAdapter= ConcernAdapter(R.layout.adapter_concern_follow,data)
        rv_concern.adapter=myAdapter
        rv_concern.layoutManager=LinearLayoutManager(activity)
        rv_concern.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

               var manager = rv_concern.layoutManager
                //firstVisibleItem 为RecyclerView 可见的第一item的position
                val firstVisibleItem =
                    (manager as LinearLayoutManager).findFirstVisibleItemPosition()

                //visibleItemCount 为RecyclerView 当前可见item的数量
                val visibleItemCount = manager.getChildCount()

                val lastVisibleItem: Int = firstVisibleItem + visibleItemCount
                //大于0说明有播放
                if (GSYVideoManager.instance().playPosition >= 0) {
                    //当前播放的位置
                    val position = GSYVideoManager.instance().playPosition
                    //对应的播放列表TAG
                    if (GSYVideoManager.instance()
                            .playTag == ConcernAdapter.TAG && (position < firstVisibleItem || position > lastVisibleItem)
                    ) {
                        if (!GSYVideoManager.isFullState(activity)) {
                            //如果滑出去了上面和下面就是否，和今日头条一样
                            GSYVideoManager.releaseAllVideos()
                            myAdapter?.notifyDataSetChanged()
                        }
                    }
                }
            }
        })
    }



    override fun initEvent() {
        presenter?.getConcernData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_concern, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ConcernFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun showConcernView(model: ArrayList<Any>) {
        data.clear()
        data.addAll(model as ArrayList<ConcernCardVo>)
        myAdapter?.notifyDataSetChanged()
    }


    override fun onPause() {
        super.onPause()
        GSYVideoManager.onPause()
    }

    override fun onResume() {
        super.onResume()
        GSYVideoManager.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoManager.releaseAllVideos()
    }
}

