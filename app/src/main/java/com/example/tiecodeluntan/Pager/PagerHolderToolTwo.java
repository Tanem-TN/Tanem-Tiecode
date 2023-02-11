package com.example.tiecodeluntan.Pager;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.tiecodeluntan.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

class PagerHolderToolTwo extends RecyclerView.ViewHolder {
    static ViewPager2 Pager1;
    static TabLayout tabLayout;

    PagerHolderToolTwo(@NonNull View itemView, Context mContext) {
        super(itemView);
        PagerAdapterTwo PagerAdapterTwo = new PagerAdapterTwo(mContext);
        Pager1 = itemView.findViewById(R.id.pager2);
        Pager1.setAdapter(PagerAdapterTwo);
        Pager1.setUserInputEnabled(true);
        tabLayout = itemView.findViewById(R.id.tab_layout);
        Setindicator(mContext);
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