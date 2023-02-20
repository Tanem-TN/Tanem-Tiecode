package com.example.tiecodeluntan.Recycler;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiecodeluntan.MainActivity;
import com.example.tiecodeluntan.R;
import com.example.tiecodeluntan.hashmaptool.哈希表;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.header.MaterialHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import cn.zhxu.okhttps.HTTP;
import cn.zhxu.okhttps.HttpResult;

public class RecyclerHolderTool extends RecyclerView.ViewHolder {
    RecyclerView Recycler;
    SmartRefreshLayout  SmartRefresh;
    RecyclerAdapter Adapter;

    public RecyclerHolderTool(@NonNull View itemView, Context mContext) {
        super(itemView);
        Adapter = new RecyclerAdapter(mContext);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        Recycler = itemView.findViewById(R.id.Recycler1);
        SmartRefresh = itemView.findViewById(R.id.SmartRefreshLayout1);
        Recycler.setAdapter(Adapter);
        Recycler.setLayoutManager(layoutManager);
        列表框();
        刷新布局(mContext);
    }
    public void 列表框(){
        Recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // RecyclerView停止滑动
                } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    // RecyclerView正在被拖拽滑动
                } else if (newState == RecyclerView.SCROLL_STATE_SETTLING) {
                    // RecyclerView正在惯性滑动
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    MainActivity.performHide();
                } else if (dy < 0) {
                    MainActivity.performShow();
                }
            }
        });
    }
    public void 刷新布局(Context mContext){
        SmartRefresh.setRefreshHeader(new MaterialHeader(mContext));
        SmartRefresh.setRefreshFooter(new ClassicsFooter(mContext));
        SmartRefresh.setOnRefreshListener(refreshlayout -> {
            Recycler.setAdapter(Adapter);
            refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
        });
        SmartRefresh.setOnLoadMoreListener(refreshlayout -> {
            refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
        });
    }
}
