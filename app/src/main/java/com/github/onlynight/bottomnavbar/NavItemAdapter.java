package com.github.onlynight.bottomnavbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by zhang on 2017/11/24.
 */

public class NavItemAdapter extends BaseAdapter {

    private String mTitles[] = {"首页", "收藏", "订单", "我的"};

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public Object getItem(int position) {
        return mTitles[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View contentView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_nav, parent, false);
        TextView textTitle = contentView.findViewById(R.id.textTitle);
        textTitle.setText(mTitles[position]);
        return contentView;
    }

}
