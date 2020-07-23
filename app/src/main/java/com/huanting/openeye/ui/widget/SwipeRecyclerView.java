package com.huanting.openeye.ui.widget;

import android.content.Context;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * 设置RecyclerView的方法
 * Created by ss036 on 2017/12/19.
 */

public class SwipeRecyclerView extends SwipeBaseView<RecyclerView> {
    public SwipeRecyclerView(Context context) {
        super(context);
    }

    public SwipeRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 返回RecyclerView本身
     *
     * @param context
     * @param attrs
     * @return
     */
    @Override
    protected RecyclerView createRecyclerView(Context context, AttributeSet attrs) {
        return new RecyclerView(context, attrs);
    }

    /**
     * 设置适配器
     *
     * @param adapter
     */
    public void setAdapter(RecyclerView.Adapter adapter) {
        mRecyclerView.setAdapter(adapter);
    }

    /**
     * 设置布局
     *
     * @param manager
     */
    public void setLayoutManager(RecyclerView.LayoutManager manager) {
        mRecyclerView.setLayoutManager(manager);
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return mRecyclerView.getLayoutManager();
    }

    /**
     * 设置滑动事件
     *
     * @param onScrollListener
     */
    public void addOnScrollListener(RecyclerView.OnScrollListener onScrollListener) {
        mRecyclerView.addOnScrollListener(onScrollListener);
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        try {
            super.onRestoreInstanceState(state);
        } catch (Exception e) {
        }
        state = null;
    }

    @Override
    public void setIsCanRefresh(boolean isCanRefresh) {
        super.setIsCanRefresh(isCanRefresh);
    }

    public void setNestedScrollingEnabled(boolean enabled) {
        mRecyclerView.setNestedScrollingEnabled(enabled);
    }

    public void addItemDecoration(RecyclerDividerItemDecoration itemDecoration){
        mRecyclerView.addItemDecoration(itemDecoration);
    }

    public void  scrollToPosition(int position){
        mRecyclerView.scrollToPosition(position);

    }

}