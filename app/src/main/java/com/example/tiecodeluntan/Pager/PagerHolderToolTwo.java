package com.example.tiecodeluntan.Pager;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.tiecodeluntan.MagicIndicatorTool.CommonNavigatorTool;
import com.example.tiecodeluntan.MagicIndicatorTool.ViewPagerHelperTool;
import com.example.tiecodeluntan.MainActivity;
import com.example.tiecodeluntan.R;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import java.util.ArrayList;

class PagerHolderToolTwo extends RecyclerView.ViewHolder {
    static ViewPager2 Pager1;
    MagicIndicator MagicIndicator;
    PagerHolderToolTwo(@NonNull View itemView, Context mContext) {
        super(itemView);
        PagerAdapterTwo PagerAdapterTwo = new PagerAdapterTwo(mContext);
        Pager1 = itemView.findViewById(R.id.pager2);
        Pager1.setAdapter(PagerAdapterTwo);
        Pager1.setUserInputEnabled(true);
        Setindicator(mContext);
        Slidingmonitor();
    }
    //解决滑动
    public void Slidingmonitor(){
        Pager1.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                if (position == Pager1.getAdapter().getItemCount()-1)  {
                    MainActivity.setUserInputEnabled(true);
                }else {
                    MainActivity.setUserInputEnabled(false);
                }
            }
        });

    }
    //设置指示器
    public  void Setindicator(Context mContext){
        ArrayList<String> AAr = new ArrayList<>();
        AAr.add("首页");
        AAr.add("精选");
        MagicIndicator = itemView.findViewById(R.id.magic_indicator);
        CommonNavigator commonNavigator = new CommonNavigator(mContext);
        CommonNavigatorTool CommonNavigatorTool = new CommonNavigatorTool(Pager1,AAr);
        commonNavigator.setAdapter(CommonNavigatorTool);
        MagicIndicator.setNavigator(commonNavigator);
        ViewPagerHelperTool.bind(MagicIndicator,Pager1);
    }

}