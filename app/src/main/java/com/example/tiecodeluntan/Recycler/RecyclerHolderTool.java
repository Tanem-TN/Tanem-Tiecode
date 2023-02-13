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

import java.util.ArrayList;

import cn.zhxu.okhttps.HTTP;
import cn.zhxu.okhttps.HttpResult;

public class RecyclerHolderTool extends RecyclerView.ViewHolder {


    public RecyclerHolderTool(@NonNull View itemView, Context mContext) {
        super(itemView);
        RecyclerAdapter Adapter = new RecyclerAdapter(mContext);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        RecyclerView Recycler = itemView.findViewById(R.id.Recycler1);
        Recycler.setAdapter(Adapter);
        Recycler.setLayoutManager(layoutManager);
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
}
