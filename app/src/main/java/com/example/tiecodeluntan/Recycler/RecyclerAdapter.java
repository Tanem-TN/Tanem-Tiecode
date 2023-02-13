package com.example.tiecodeluntan.Recycler;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiecodeluntan.R;
import com.example.tiecodeluntan.hashmaptool.哈希表;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

import cn.zhxu.okhttps.HTTP;
import cn.zhxu.okhttps.HttpResult;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolderTool> {
    Context mContext;
    ArrayList 集合1 = new ArrayList<>();

    public RecyclerAdapter(Context mContext) {
        this.mContext = mContext;
        数据();
    }


    public void 数据() {
        HTTP okhttp = HTTP.builder().build();
        okhttp.async("http://101.33.227.148/api")
                .setOnResponse((HttpResult res) -> {
                    HttpResult.Body boy = res.getBody();
                    JsonObject jsonObject = JsonParser.parseString(boy.toString()).getAsJsonObject();
                    JsonArray array = jsonObject.getAsJsonArray("thread_list");
                    for (int i = 0; i < array.size(); i++) {
                        哈希表 hxb1 = new 哈希表();
                        JsonObject item = array.get(i).getAsJsonObject();
                        // 取出指定字段
                        String tid = item.get("tid").getAsString();             // 帖子ID
                        String summary = item.get("summary").getAsString();         // 帖子摘要
                        String fid = item.get("fid").getAsString();             // 论坛版块ID
                        String uid = item.get("uid").getAsString();             // 用户ID
                        String pid = item.get("pid").getAsString();             // 帖子内容ID
                        String title = item.get("title").getAsString();         // 帖子标题
                        int nos = item.get("nos").getAsInt();                   // 反对次数
                        int goods = item.get("goods").getAsInt();               // 支持次数
                        int jing = item.get("jing").getAsInt();                 // 帖子精华度
                        int shenhe = item.get("shenhe").getAsInt();             // 帖子审核状态
                        String img = item.get("img").getAsString();             // 帖子缩略图（图片路径）
                        String user = item.getAsJsonObject("userdata").get("user").getAsString();      // 发帖人员用户名
                        int digest = item.get("digest").getAsInt();             // 是否加精（0-未加精，1-已加精）
                        int hide = item.get("hide").getAsInt();                 // 是否隐藏（0-未隐藏，1-已隐藏）
                        int top = item.get("top").getAsInt();                   // 是否置顶（0-未置顶，1-置顶）
                        hxb1.添加项目("标题", title);
                        hxb1.添加项目("用户名", user);
                        hxb1.添加项目("支持", goods);
                        hxb1.添加项目("反对", nos);
                        hxb1.添加项目("帖子摘要", summary);
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
    public RecyclerAdapter.RecyclerViewHolderTool onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerlist, parent, false);
        return new RecyclerViewHolderTool(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.RecyclerViewHolderTool holder, int position) {
        哈希表 hxb1 = (哈希表) 集合1.get(position);
        holder.用户名.setText(hxb1.取项目("用户名").toString());
        holder.帖子标题.setText(hxb1.取项目("标题").toString());
        holder.帖子摘要.setText(hxb1.取项目("帖子摘要").toString());
    }

    @Override
    public int getItemCount() {
        return 集合1.size(); // 数据获取中，返回0
    }

    public static class RecyclerViewHolderTool extends RecyclerView.ViewHolder {
        TextView 用户名;
        TextView 帖子标题;
        TextView 帖子摘要;

        RecyclerViewHolderTool(@NonNull View itemView) {
            super(itemView);
            用户名 = itemView.findViewById(R.id.用户名);
            帖子标题 = itemView.findViewById(R.id.帖子标题);
            帖子摘要 = itemView.findViewById(R.id.帖子摘要);
        }

    }
}

