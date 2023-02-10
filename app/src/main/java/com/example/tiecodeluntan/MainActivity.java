package com.example.tiecodeluntan;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.tiecodeluntan.Pager.PagerAdapter;

import cn.zhxu.data.Mapper;
import cn.zhxu.okhttps.HttpCall;
import cn.zhxu.okhttps.HttpResult;
import cn.zhxu.okhttps.OkHttps;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Pager();
    }
    public void Pager(){
        ViewPager2 vPager = findViewById(R.id.Pager1);
        RecyclerView.Adapter PagerAdapter = new PagerAdapter(this);
        vPager.setAdapter(PagerAdapter);
        HttpCall httpCall = OkHttps.async("/users/1")
                .setOnResponse((HttpResult res) -> {
                    // 得到目标数据
                    HttpResult.Body boy = res.getBody();
                })
                .get();
    }
}