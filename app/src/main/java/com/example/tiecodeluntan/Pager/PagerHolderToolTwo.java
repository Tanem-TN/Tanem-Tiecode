package com.example.tiecodeluntan.Pager;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.tiecodeluntan.MainActivity;
import com.example.tiecodeluntan.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

class PagerHolderToolTwo extends RecyclerView.ViewHolder {
    static ViewPager2 Pager1;
    static TabLayout tabLayout;

    LinearLayout 线性布局1;

    PagerHolderToolTwo(@NonNull View itemView, Context mContext) {
        super(itemView);
        PagerAdapterTwo PagerAdapterTwo = new PagerAdapterTwo(mContext);
        Pager1 = itemView.findViewById(R.id.pager2);
        Pager1.setAdapter(PagerAdapterTwo);
        Pager1.setUserInputEnabled(true);
        tabLayout = itemView.findViewById(R.id.tab_layout);
        Setindicator(mContext);
        线性布局1 = itemView.findViewById(R.id.高度);
        MainActivity.设置布局高度(线性布局1,MainActivity.取状态栏高度(mContext));
    }

    //设置指示器
    public void Setindicator(Context mContext) {
        new TabLayoutMediator(tabLayout, Pager1, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("首页");
                    break;
                default:
                    tab.setText("精选");
            }
        }).attach();
    }

}