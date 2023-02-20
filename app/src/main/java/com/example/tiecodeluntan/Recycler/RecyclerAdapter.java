package com.example.tiecodeluntan.Recycler;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiecodeluntan.R;
import com.example.tiecodeluntan.glide.GlideTool;
import com.example.tiecodeluntan.hashmaptool.哈希表;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import cc.shinichi.library.ImagePreview;
import cn.zhxu.okhttps.HTTP;
import cn.zhxu.okhttps.HttpResult;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolderTool> {
    Context mContext;
    static ArrayList 集合1 = new ArrayList<>();

    public RecyclerAdapter(Context mContext) {
        this.mContext = mContext;
        数据();
    }


    public void 数据() {
        集合1.clear();
        HTTP okhttp = HTTP.builder().build();
        okhttp.async("http://101.33.227.148/api")
                .setOnResponse((HttpResult res) -> {
                    HttpResult.Body boy = res.getBody();
                    JsonObject jsonObject = JsonParser.parseString(boy.toString()).getAsJsonObject();
                    JsonArray array = jsonObject.getAsJsonArray("thread_list");
                    JsonObject imgObject = null;
                    for (int i = 0; i < array.size(); i++) {
                        哈希表 hxb1 = new 哈希表();
                        JsonObject item = array.get(i).getAsJsonObject();
                        // 取出指定字段
                        String tid = item.get("tid").getAsString();             // 帖子ID
                        String summary = item.get("summary").getAsString();         // 帖子摘要
                        String fid = item.get("fid").getAsString();             // 论坛版块ID
                        String uid = item.get("uid").getAsString();             // 用户ID
                        String img = item.get("img").getAsString();             //图片
                        String pid = item.get("pid").getAsString();             // 帖子内容ID
                        String title = item.get("title").getAsString();         // 帖子标题
                        int nos = item.get("nos").getAsInt();                   // 反对次数
                        int goods = item.get("goods").getAsInt();               // 支持次数
                        int jing = item.get("jing").getAsInt();                 // 帖子精华度
                        int shenhe = item.get("shenhe").getAsInt();             // 帖子审核状态
                        String user = item.getAsJsonObject("userdata").get("user").getAsString();      // 发帖人员用户名
                        int digest = item.get("digest").getAsInt();             // 是否加精（0-未加精，1-已加精）
                        int hide = item.get("hide").getAsInt();                 // 是否隐藏（0-未隐藏，1-已隐藏）
                        int top = item.get("top").getAsInt();                   // 是否置顶（0-未置顶，1-置顶）
                        // 帖子缩略图（图片路径）
                        hxb1.添加项目("标题", title);
                        hxb1.添加项目("用户名", user);
                        hxb1.添加项目("支持", goods);
                        hxb1.添加项目("反对", nos);
                        hxb1.添加项目("帖子摘要", summary);
                        hxb1.添加项目("图片", img);
                        集合1.add(hxb1);
                    }
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(() -> {
                        notifyDataSetChanged();
                    });
                })
                .get();
    }

    @NonNull
    @Override
    public RecyclerViewHolderTool onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerlist, parent, false);
        return new RecyclerViewHolderTool(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolderTool holder, int position) {
        哈希表 hxb1 = (哈希表) 集合1.get(position);
        holder.用户名.setText(hxb1.取项目("用户名").toString());
        holder.帖子标题.setText(hxb1.取项目("标题").toString());
        holder.帖子摘要.setText(hxb1.取项目("帖子摘要").toString());
        九宫格图片框(holder,hxb1);
    }

    public void 九宫格图片框(RecyclerViewHolderTool holder,哈希表 hxb1){
        String[] imageUrls = hxb1.取项目("图片").toString().split(",");
        int imageCount = imageUrls.length;
        ImageView[] imageViews = new ImageView[]{holder.图片1, holder.图片2, holder.图片3, holder.图片4, holder.图片5, holder.图片6, holder.图片7, holder.图片8, holder.图片9};
// 根据图片数量调整显示的图片框个数
        if (imageCount < 3) {
            imageCount = 1;
        } else if (imageCount < 6) {
            imageCount = 3;
        } else if (imageCount < 9) {
            imageCount = 6;
        } else {
            imageCount = 9;
        }

// 设置图片框的可见性
        for (int i = 0; i < imageCount; i++) {
            ImageView imageView = imageViews[i];
            imageView.setVisibility(View.VISIBLE);

        }

// 显示图片
        for (int i = 0; i < imageCount; i++) {
            String imageUrl = imageUrls[i];
            ImageView imageView = imageViews[i];
            List<String> a =new  ArrayList<>();
            a.add(imageUrl);
            GlideTool.加载图片(imageView, imageUrl, null, null);
            int finalI = i;
            imageView.setOnClickListener(view -> {
                ImagePreview
                        .getInstance()
                        // 上下文，必须是activity，不需要担心内存泄漏，本框架已经处理好；
                        .setContext(imageView.getContext())

                        // 设置从第几张开始看（索引从0开始）
                        .setIndex(0)

                        //=================================================================================================
                        // 有三种设置数据集合的方式，根据自己的需求进行三选一：
                        // 1：第一步生成的imageInfo List

                        // 2：直接传url List
//                        .setImageList(a)

                        .setShowErrorToast(true)
                        .setImage(imageUrl)
                        // 3：只有一张图片的情况，可以直接传入这张图片的url
                        //.setImage(String image)
                        //=================================================================================================

                        // 开启预览
                        .start();
            });
        }

// 隐藏多余的图片框
        for (int i = imageCount; i < 9; i++) {
            ImageView imageView = imageViews[i];
            imageView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return 集合1.size();
    }

    @SuppressWarnings("NonAsciiCharacters")
    public static class RecyclerViewHolderTool extends RecyclerView.ViewHolder {
        TextView 用户名;
        TextView 帖子标题;
        TextView 帖子摘要;
        ImageView 图片1;
        ImageView 图片2;
        ImageView 图片3;
        ImageView 图片4;
        ImageView 图片5;
        ImageView 图片6;
        ImageView 图片7;
        ImageView 图片8;
        ImageView 图片9;
        MaterialCardView 卡片布局1;
        ArrayList<Object> 集合 = new ArrayList<>();

        RecyclerViewHolderTool(@NonNull View itemView) {
            super(itemView);
            用户名 = itemView.findViewById(R.id.用户名);
            帖子标题 = itemView.findViewById(R.id.帖子标题);
            帖子摘要 = itemView.findViewById(R.id.帖子摘要);
            图片1 = itemView.findViewById(R.id.imageView01);
            图片2 = itemView.findViewById(R.id.imageView02);
            图片3 = itemView.findViewById(R.id.imageView03);
            图片4 = itemView.findViewById(R.id.imageView04);
            图片5 = itemView.findViewById(R.id.imageView05);
            图片6 = itemView.findViewById(R.id.imageView06);
            图片7 = itemView.findViewById(R.id.imageView07);
            图片8 = itemView.findViewById(R.id.imageView08);
            图片9 = itemView.findViewById(R.id.imageView09);
            卡片布局1 = itemView.findViewById(R.id.MaterialCardView1);
        }

    }
}

