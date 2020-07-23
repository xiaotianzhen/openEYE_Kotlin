package com.huanting.openeye.ui.widget;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huanting.openeye.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * RecyclerView的刷新控件
 * Created by ss036 on 2017/12/19.
 */

public abstract class SwipeBaseView<T extends RecyclerView> extends LinearLayout {
    // 拉动时候的状态
    private static final int PULL_UP_STATE = 0;
    private static final int PULL_DOWN_STATE = 1;
    // 恢复时候的状态
    private static final int PULL_TO_REFRESH = 2;
    private static final int RELEASE_TO_REFRESH = 3;
    private static final int REFRESHING = 4;
    /**
     * 底部刷新接口
     */
    private OnFooterRefreshListener mOnFooterRefreshListener;
    /**
     * 头部刷新接口
     */
    private OnHeaderRefreshListener mOnHeaderRefreshListener;
    /**
     * 改变向下的箭头，改变箭头方向
     */
    private RotateAnimation mFlipAnimation;
    /**
     * 变为逆向的箭头，旋转
     */
    private RotateAnimation mReverseFlipAnimation;
    /**
     * 转换布局
     */
    private LayoutInflater mInflater;

    /**
     * 头部View
     */
    private View mHeaderView;
    /**
     * 头部View的TextView
     */
    private TextView mHeaderTextView;
    /**
     * 头部View的时间view
     */
    private TextView mHeaderUpdateTextView;
    /**
     * 头部View的进度条
     */
    private ProgressBar mHeaderProgressBar;
    /**
     * 头部Vie的高度
     */
    private int mHeaderViewHeight;
    /**
     * RecyclerView的泛型
     */
    protected T mRecyclerView;
    /**
     * 底部View
     */
    private View mFooterView;
    /**
     * 底部View的子Vie
     */
    private ImageView mFooterImageView;
    /**
     * 底部View的提示View
     */
    private TextView mFooterTextView;
    /**
     * 底部View的进度条
     */
    private ProgressBar mFooterProgressBar;
    /**
     * 底部View的布局
     */
    private RelativeLayout mFooterTouch;
    /**
     * 底部View的高度
     */
    private int mFooterViewHeight;
    /**
     * 头部View的高度
     */
    private int mHeaderState;
    /**
     * 底部View的高度
     */
    private int mFooterState;
    /**
     * 最后一次的Y的位置
     */
    private int mLastMotionY;
    /**
     * 拉动的状态
     */
    private int mPullState;
    /**
     * 数据是否为空
     */
    private boolean isListNull = false;
    /**
     * 上下文
     */
    private Context mContext;

    /**
     * 非否可以刷新
     *
     * @param context
     */
    private boolean isCanRefresh = true;

    /**
     * 非否可以加载更多
     *
     * @param context
     */
    private boolean isCanLoadMore = true;


    public SwipeBaseView(Context context) {
        super(context);
    }

    public SwipeBaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
        mContext = context;
    }

    /**
     * 初始化控件
     *
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
        //动画
        mFlipAnimation = new RotateAnimation(0, -180, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5F);
        mFlipAnimation.setInterpolator(new LinearInterpolator());
        mFlipAnimation.setDuration(250);
        mFlipAnimation.setFillAfter(false);
        mReverseFlipAnimation = new RotateAnimation(-180, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5F);
        mReverseFlipAnimation.setInterpolator(new LinearInterpolator());
        mReverseFlipAnimation.setDuration(250);
        mReverseFlipAnimation.setFillAfter(true);


        //添加转换布局
        mInflater = LayoutInflater.from(getContext());
        addHeaderView();
        //返回RecyclerView
        mRecyclerView = createRecyclerView(context, attrs);
        mRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mRecyclerView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        //添加RecyclerView进布局
        addView(mRecyclerView);
    }

    /**
     * 添加头部布局
     */
    private void addHeaderView() {
        //头部布局
        mHeaderView = mInflater.inflate(R.layout.refresh_header, this, false);
        mHeaderTextView = (TextView) mHeaderView.findViewById(R.id.swipe_to_refresh_text);
        mHeaderUpdateTextView = (TextView) mHeaderView.findViewById(R.id.swipe_to_refresh_updated_at);
        mHeaderProgressBar = (ProgressBar) mHeaderView.findViewById(R.id.swipe_to_refresh_progress);
        mHeaderUpdateTextView.setText("最近更新:" + getFormatDateString("MM-dd HH:mm"));
        //调整View的大小
        measureView(mHeaderView);
        //获取头View的高度
        mHeaderViewHeight = mHeaderView.getMeasuredHeight();
        //设置布局
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, mHeaderViewHeight);
        //设置Margin为负的View高度时候，会隐藏起来
        params.topMargin = -(mHeaderViewHeight);
        //将布局添加上去
        addView(mHeaderView, params);
    }

    /**
     * 添加底部View
     */
    private void addFooterView() {
        //转换布局
        mFooterView = mInflater.inflate(R.layout.refresh_footer, this, false);
        mFooterImageView = (ImageView) mFooterView.findViewById(R.id.swipe_to_load_image);
        mFooterTextView = (TextView) mFooterView.findViewById(R.id.swipe_to_load_text);
        mFooterProgressBar = (ProgressBar) mFooterView.findViewById(R.id.swipe_to_load_progress);
        //添加底部View
        measureView(mFooterView);
        //返回底部View的高度
        mFooterViewHeight = mFooterView.getMeasuredHeight();
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, mFooterViewHeight);
        //设置View的布局
        addView(mFooterView, params);
    }

    /**
     * 当View的XML布局加载完毕后调用的方法
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        addFooterView();
    }

    /**
     * 触摸事件分发(true的时候不分发)
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE://刷新时禁止滑动
                if (mHeaderState == REFRESHING || mFooterState == REFRESHING) {
                    return true;
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 事件拦截
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int y = (int) ev.getRawY();
        int x = (int) ev.getRawX();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 首先拦截down事件,记录y坐标
                mLastMotionY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaY = y - mLastMotionY;
                if (deltaY > 0 && !isCanRefresh) {
                    return false;
                } else {
                    if (isRefreshViewScroll(deltaY)) {
                        return true;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return false;
    }

    /**
     * 触摸事件
     * 如果在onInterceptTouchEvent()方法中没有拦截(即onInterceptTouchEvent()方法中 return
     * false)PullBaseView 的子View来处理;否则由下面的方法来处理(即由PullToRefreshView自己来处理)
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // onInterceptTouchEvent已经记录
                // mLastMotionY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaY = y - mLastMotionY;
                if (mPullState == PULL_DOWN_STATE) {
                    if (isCanRefresh)
                        headerPrepareToRefresh(deltaY);
                } else if (mPullState == PULL_UP_STATE) {
                    //先设置gone，防止和scrollview嵌套的时候底部加载出来
                    if (isCanLoadMore) {
                        footerPrepareToRefresh(deltaY);
                    }
                }
                mLastMotionY = y;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                int topMargin = getHeaderTopMargin();
                if (mPullState == PULL_DOWN_STATE) {
                    if (topMargin >= 0) {
                        // 开始刷新
                        headerRefreshing();
                    } else {
                        // 还没有执行刷新，重新隐藏
                        setHeaderTopMargin(-mHeaderViewHeight);
                    }
                } else if (mPullState == PULL_UP_STATE) {
                    if (Math.abs(topMargin) >= mHeaderViewHeight + mFooterViewHeight) {
                        // 开始执行footer 刷新
                        footerRefreshing();
                    } else {
                        setHeaderTopMargin(-mHeaderViewHeight);
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 是否应该到了父View,即PullToRefreshView滑动
     *
     * @param deltaY , deltaY > 0 是向下运动,< 0是向上运动
     * @return
     */
    private boolean isRefreshViewScroll(int deltaY) {
        if (mHeaderState == REFRESHING || mFooterState == REFRESHING) {
            return false;
        }
        if (deltaY >= -20 && deltaY <= 20)
            return false;

        if (mRecyclerView != null) {
            // 子view(ListView or GridView)滑动到最顶端
            // deltaY > 0 是向下运动,< 0是向上运动
            if (deltaY > 0) {
                View child = mRecyclerView.getChildAt(0);
                if (child == null) {
                    // 如果mRecyclerView中没有数据,不拦截
                    return false;
                }
                //解决scrollview嵌套此布局出现bug
                //&&mRecyclerView.getHeight()<Utils.getWindowMetrics(mContext)[1]
                if (isScrollTop() && child.getTop() == 0) {
                    mPullState = PULL_DOWN_STATE;
                    return true;
                }
                int top = child.getTop();
                int padding = mRecyclerView.getPaddingTop();
                if (isScrollTop() && Math.abs(top - padding) <= 8) {// 这里之前用3可以判断,但现在不行,还没找到原因
                    mPullState = PULL_DOWN_STATE;
                    return true;
                }

            } else if (deltaY < 0) {
                View lastChild = mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1);
                if (lastChild == null) {
                    // 如果mRecyclerView中没有数据,不拦截
                    return false;
                }
                // 最后一个子view的Bottom小于父View的高度说明mRecyclerView的数据没有填满父view,
                // 等于父View的高度说明mRecyclerView已经滑动到最后
                if (lastChild.getBottom() <= getHeight() && isScrollBottom()) {
                    mPullState = PULL_UP_STATE;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断mRecyclerView是否滑动到顶部
     *
     * @return
     */
    boolean isScrollTop() {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        if (linearLayoutManager.findFirstVisibleItemPosition() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断mRecyclerView是否滑动到底部
     *
     * @return
     */
    boolean isScrollBottom() {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        if (linearLayoutManager.findLastVisibleItemPosition() == (mRecyclerView.getAdapter().getItemCount() - 1)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * header 准备刷新,手指移动过程,还没有释放
     *
     * @param deltaY ,手指滑动的距离
     */
    private void headerPrepareToRefresh(int deltaY) {
        int newTopMargin = changingHeaderViewTopMargin(deltaY);
        // 当header view的topMargin>=0时，说明已经完全显示出来了,修改header view 的提示状态
        if (newTopMargin >= 0 && mHeaderState != RELEASE_TO_REFRESH) {
            mHeaderTextView.setText("松开刷新");
           mHeaderUpdateTextView.setVisibility(View.VISIBLE);
            mHeaderState = RELEASE_TO_REFRESH;
        } else if (newTopMargin < 0 && newTopMargin > -mHeaderViewHeight) {// 拖动时没有释放
            mHeaderTextView.setText("下拉刷新");
            mHeaderState = PULL_TO_REFRESH;
        }
    }

    /**
     * footer 准备刷新,手指移动过程,还没有释放 移动footer view高度同样和移动header view
     * 高度是一样，都是通过修改header view的topmargin的值来达到
     *
     * @param deltaY ,手指滑动的距离
     */
    private void footerPrepareToRefresh(int deltaY) {
        int newTopMargin = changingHeaderViewTopMargin(deltaY);
        // 如果header view topMargin 的绝对值大于或等于header + footer 的高度
        // 说明footer view 完全显示出来了，修改footer view 的提示状态
        if (isListNull) {
            mFooterTextView.setText("已经全部加载完毕");
            mFooterImageView.setVisibility(GONE);
        } else {
            mFooterImageView.setVisibility(VISIBLE);
            if (Math.abs(newTopMargin) >= (mHeaderViewHeight + mFooterViewHeight) && mFooterState != RELEASE_TO_REFRESH) {
                mFooterTextView.setText("松开加载更多");
                mFooterImageView.clearAnimation();
                mFooterImageView.startAnimation(mFlipAnimation);
                mFooterState = RELEASE_TO_REFRESH;
            } else if (Math.abs(newTopMargin) < (mHeaderViewHeight + mFooterViewHeight)) {
                mFooterImageView.clearAnimation();
                mFooterImageView.startAnimation(mFlipAnimation);
                mFooterTextView.setText("上拉加载更多");
                mFooterState = PULL_TO_REFRESH;
            }
        }
    }

    /**
     * 头部刷新
     */
    public void headerRefreshing() {
        mHeaderState = REFRESHING;
        setHeaderTopMargin(0);
        mHeaderProgressBar.setVisibility(View.VISIBLE);
        mHeaderTextView.setText("正在刷新");
        if (mOnHeaderRefreshListener != null) {
            mOnHeaderRefreshListener.onHeaderRefresh(this);
        }
    }

    /**
     * 底部刷新
     */
    private void footerRefreshing() {
        mFooterState = REFRESHING;
        int top = mHeaderViewHeight + mFooterViewHeight;
        setHeaderTopMargin(-top);
        if (!isListNull) {
            mFooterImageView.setVisibility(View.GONE);
            mFooterImageView.clearAnimation();
            mFooterImageView.setImageDrawable(null);
            mFooterTextView.setText("正在加载更多数据");
            mFooterProgressBar.setVisibility(View.VISIBLE);
            if (mOnFooterRefreshListener != null) {
                mOnFooterRefreshListener.onFooterRefresh(this);
            }
        } else {
            mFooterTextView.setText("已经全部加载完毕");
            onFooterRefreshComplete();
        }

    }

    /**
     * header view 完成更新后恢复初始状态
     */
    public void onHeaderRefreshComplete() {
        setHeaderTopMargin(-mHeaderViewHeight);
        mHeaderTextView.setText("下拉刷新");
        mHeaderUpdateTextView.setText("最近更新：" + getFormatDateString("MM-dd HH:mm"));
        mHeaderState = PULL_TO_REFRESH;
        isListNull = false;
    }

    /**
     * footer view 完成更新后恢复初始状态
     */
    public void onFooterRefreshComplete() {
        mFooterImageView.setVisibility(View.GONE);
        mFooterImageView.setImageResource(R.mipmap.ic_pulltorefresh_arrow_up);
        if (!isListNull) {
            mFooterTextView.setText("上拉加载更多");
        }
        mFooterProgressBar.setVisibility(View.GONE);
        mFooterState = PULL_TO_REFRESH;
        setHeaderTopMargin(-mHeaderViewHeight);
        if (mRecyclerView != null) {
            mRecyclerView.scrollToPosition(mRecyclerView.getAdapter().getItemCount() - 1);
        }
    }

    /**
     * 返回头部View状态
     *
     * @return
     */
    public boolean isHeadRefreshStatus() {
        return mHeaderState == REFRESHING;
    }

    /**
     * 返回底部View状态
     *
     * @return
     */
    public boolean isFooterRefreshStatus() {
        return mFooterState == REFRESHING;
    }

    /**
     * 设置header view 的topMargin的值
     *
     * @param topMargin ，为0时，说明header view 刚好完全显示出来； 为-mHeaderViewHeight时，说明完全隐藏了
     * @description
     */
    private void setHeaderTopMargin(int topMargin) {
        LayoutParams params = (LayoutParams) mHeaderView.getLayoutParams();
        params.topMargin = topMargin;
        mHeaderView.setLayoutParams(params);
        invalidate();
    }

    /**
     * 修改Header view top margin的值
     *
     * @param deltaY
     * @description
     */
    private int changingHeaderViewTopMargin(int deltaY) {
        LayoutParams params = (LayoutParams) mHeaderView.getLayoutParams();
        float newTopMargin = params.topMargin + deltaY * 0.3f;
        // 这里对上拉做一下限制,因为当前上拉后然后不释放手指直接下拉,会把下拉刷新给触发了,感谢网友yufengzungzhe的指出
        // 表示如果是在上拉后一段距离,然后直接下拉
        if (deltaY > 0 && mPullState == PULL_UP_STATE && Math.abs(params.topMargin) <= mHeaderViewHeight) {
            return params.topMargin;
        }
        // 同样地,对下拉做一下限制,避免出现跟上拉操作时一样的bug
        if (deltaY < 0 && mPullState == PULL_DOWN_STATE && Math.abs(params.topMargin) >= mHeaderViewHeight) {
            return params.topMargin;
        }
        params.topMargin = (int) newTopMargin;
        mHeaderView.setLayoutParams(params);
        invalidate();
        return params.topMargin;
    }

    /**
     * 设置是否可以刷新
     *
     * @param isCanRefresh
     * @return
     */

    public void setIsCanRefresh(boolean isCanRefresh) {
        this.isCanRefresh = isCanRefresh;
    }


    /**
     * 设置是否可以加载更多
     *
     * @param isCanLoadMore
     * @return
     */

    public void setIsCanLoadMore(boolean isCanLoadMore) {
        this.isCanLoadMore = isCanLoadMore;
    }


    /**
     * 获取当前header view 的topMargin
     *
     * @description
     */
    private int getHeaderTopMargin() {
        LayoutParams params = (LayoutParams) mHeaderView.getLayoutParams();
        return params.topMargin;
    }

    /**
     * 获取时间方法
     *
     * @param format
     * @return
     */
    public static final String getFormatDateString(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

    /**
     * 测量控件的量进行调整
     *
     * @param child
     */
    private void measureView(View child) {
        ViewGroup.LayoutParams p = child.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight, MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }

    /**
     * 设置数据是否为空
     *
     * @param listNull
     */
    public void setListNull(boolean listNull) {
        isListNull = listNull;
        if (listNull)
            mFooterTextView.setText("已经全部加载完毕");
        else
            mFooterTextView.setText("上拉加载更多");
        mFooterImageView.setVisibility(GONE);
    }


    /**
     * 设置头部监听事件
     *
     * @description
     */
    public void setOnHeaderRefreshListener(OnHeaderRefreshListener headerRefreshListener) {
        mOnHeaderRefreshListener = headerRefreshListener;
    }

    /**
     * 设置底部监听事件
     *
     * @param footerRefreshListener
     */
    public void setOnFooterRefreshListener(OnFooterRefreshListener footerRefreshListener) {
        mOnFooterRefreshListener = footerRefreshListener;
    }

    /**
     * 底部的View回调接口
     */
    public interface OnFooterRefreshListener {
        void onFooterRefresh(SwipeBaseView view);
    }

    /**
     * 头部的View回调接口
     */
    public interface OnHeaderRefreshListener {
        void onHeaderRefresh(SwipeBaseView view);
    }

    protected abstract T createRecyclerView(Context context, AttributeSet attrs);
}
