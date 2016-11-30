package com.ya.deliverydemo;

import android.content.Context;
import android.util.AttributeSet;

import com.malinskiy.superrecyclerview.SuperRecyclerView;

/**
 * Created by YaoFengjuan on 2016/11/29.
 */

public class SuperRecyclerNew extends SuperRecyclerView {

    public SuperRecyclerNew(Context context) {
        super(context);
    }

    public SuperRecyclerNew(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SuperRecyclerNew(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void requestLayout() {
        super.requestLayout();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mProgress.setVisibility(GONE);
        mProgressView.setVisibility(GONE);
        mMoreProgress.setVisibility(GONE);
        mMoreProgressView.setVisibility(GONE);
    }
}
