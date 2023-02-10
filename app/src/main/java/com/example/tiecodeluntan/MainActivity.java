package com.example.tiecodeluntan;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.tiecodeluntan.Pager.PagerAdapter;

import cn.zhxu.data.Mapper;
import cn.zhxu.okhttps.HTTP;
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
        HTTP htpp = HTTP.builder().build();
        htpp.async("http://101.33.227.148:80/api")
                .bodyType("application/json; charset=utf-8")
                .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36 Edg/109.0.1518.78")
                .setOnResponse((HttpResult res) -> {
                    // 得到目标数据
                    HttpResult.Body boy = res.getBody();
                    Log.i("htpps",boy.toString());
                })
                .get();
  }
}