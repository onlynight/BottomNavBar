package com.github.onlynight.bottomnavbar.library;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Calendar;

/**
 * Created by zhang on 2017/11/24.
 * bottom nav bar view
 */

public class BottomNavBar extends ViewGroup {

    private OnItemSelectedListener mOnItemSelectedListener;
    private BaseAdapter mAdapter;
    private long mDoubleClickBlank = 1000;

    private DataSetObserver mDataSetObserver = new DataSetObserver() {
        @Override
        public void onChanged() {
            addAdapterView();
            requestLayout();
        }
    };

    public BottomNavBar(Context context) {
        super(context);
    }

    public BottomNavBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BottomNavBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(21)
    public BottomNavBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setDoubleClickBlank(long doubleClickBlank) {
        this.mDoubleClickBlank = doubleClickBlank;
    }

    public long getDoubleClickBlank() {
        return mDoubleClickBlank;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (mAdapter != null && mAdapter.getCount() > 0) {
            int width = getWidth();
            int height = getHeight();

            int size = mAdapter.getCount();
            float eachWidth = width / (float) size;

            for (int i = 0; i < mAdapter.getCount(); i++) {
                View childView = getChildAt(i);
                childView.layout((int) (l + i * eachWidth), 0, (int) (l + (i + 1) * eachWidth), height);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    public void setAdapter(BaseAdapter adapter) {
        if (mAdapter != null && mDataSetObserver != null) {
            mAdapter.unregisterDataSetObserver(mDataSetObserver);
        }

        this.mAdapter = adapter;
        if (mAdapter != null) {
            mAdapter.registerDataSetObserver(mDataSetObserver);
        }

        addAdapterView();
    }

    private void addAdapterView() {
        removeAllViews();
        if (mAdapter != null) {
            for (int i = 0; i < mAdapter.getCount(); i++) {
                final int index = i;
                View view = mAdapter.getView(index, null, this);
                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        long time = Calendar.getInstance().getTime().getTime();
                        Object tag = view.getTag();

                        if (tag == null) {
                            tag = new DoubleClickBean();
                        }

                        if (mOnItemSelectedListener != null) {
                            mOnItemSelectedListener.onTabSelected(index);
                            if (tag instanceof DoubleClickBean) {
                                if ((time - ((DoubleClickBean) tag).time) <= mDoubleClickBlank) {
                                    if (((DoubleClickBean) tag).clickTimes >= 1) {
                                        ((DoubleClickBean) tag).clickTimes = 0;
                                        mOnItemSelectedListener.onTabDoubleClick(index);
                                    } else {
                                        ((DoubleClickBean) tag).clickTimes += 1;
                                    }
                                    ((DoubleClickBean) tag).time = 0;
                                } else {
                                    ((DoubleClickBean) tag).time = time;
                                    ((DoubleClickBean) tag).clickTimes = 1;
                                }
                            }
                        }

                        setSelect(index);
                        view.setTag(tag);
                    }
                });
                addView(view);
            }

            requestLayout();
        }
    }

    /**
     * set select tab item
     *
     * @param position selected position
     */
    public void setSelect(int position) {
        if (mAdapter != null && position < mAdapter.getCount()) {
            View view;
            for (int i = 0; i < mAdapter.getCount(); i++) {
                view = getChildAt(i);
                view.setSelected(position == i);
            }
        }
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.mOnItemSelectedListener = onItemSelectedListener;
    }

    /**
     * on item selected listener
     */
    public interface OnItemSelectedListener {

        void onTabSelected(int position);

        void onTabDoubleClick(int position);

    }

    private static class DoubleClickBean {

        public int clickTimes;
        public long time;

    }

}
