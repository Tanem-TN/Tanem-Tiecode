package com.example.tiecodeluntan;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.tiecodeluntan.Pager.PagerAdapter;
import com.google.android.material.bottomappbar.BottomAppBar;

import cn.zhxu.okhttps.HTTP;
import cn.zhxu.okhttps.HttpResult;

public class MainActivity extends AppCompatActivity {
    public static BottomAppBar bottBar;
    public static ViewPager2 vPager;
    public MainActivity(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottBar = findViewById(R.id.bottomAppBar1);
        vPager = findViewById(R.id.Pager1);
        MainActivity.setUserInputEnabled(false);
        Pager();
        Slidingmonitor();
    }
    public void Pager(){
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
  public static void performHide(){
        bottBar.performHide();
  }
    public static void performShow(){
        bottBar.performShow();
    }
    public static void setUserInputEnabled(boolean whether){
        vPager.setUserInputEnabled(whether);
    }
    //解决滑动问题
    public void Slidingmonitor(){
        vPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                if (position == 0)  {
                    MainActivity.setUserInputEnabled(false);
                }else {
                    MainActivity.setUserInputEnabled(true);
                }
            }
        });

    }
}