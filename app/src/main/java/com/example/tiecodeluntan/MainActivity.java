package com.example.tiecodeluntan;

import android.annotation.SuppressLint;
import android.database.CursorJoiner;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.tiecodeluntan.Pager.PagerAdapter;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

@SuppressWarnings("NonAsciiCharacters")
public class MainActivity extends AppCompatActivity {
    public static BottomAppBar bottBar;
    public static ViewPager2 vPager;

    public static BottomNavigationView bottomAppBar1;

    public MainActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottBar = findViewById(R.id.bottomAppBar1);
        vPager = findViewById(R.id.Pager1);
        bottomAppBar1 = findViewById(R.id.BottomNavigationView1);
        Pager();
        setOnItemSelectedListener();
        沉浸模式();
    }

    public void Pager() {
        PagerAdapter PagerAdapter = new PagerAdapter(this);
        vPager.setAdapter(PagerAdapter);

    }

    public static void performHide() {
        bottBar.performHide();
    }

    public static void performShow() {
        bottBar.performShow();
    }

    @SuppressLint("NonConstantResourceId")
    public static void setOnItemSelectedListener() {
        bottomAppBar1.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_home:
                    vPager.setCurrentItem(0);
                    return true;
                case R.id.action_search:
                    vPager.setCurrentItem(1);
                    return true;
                case R.id.action_settings:
                    vPager.setCurrentItem(2);
                    return true;
            }
            return false;
        });
        vPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                bottomAppBar1.getMenu().getItem(position).setChecked(true);
            }
        });
    }
    public void 导航栏颜色(){
        this.getWindow().setNavigationBarColor(0x00000000);
    }
    public void 状态栏颜色(){
        this.getWindow().setStatusBarColor(0x00000000);
    }
    public void 状态栏字体黑色(boolean 是否黑色){
        if (是否黑色) {
            this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        }
    }
    public void 沉浸模式(){
        状态栏字体黑色(true);
        导航栏颜色();
        状态栏颜色();
    }
}
//    HTTP htpp = HTTP.builder().build();
//            htpp.async("http://101.33.227.148:80/api")
//                    .bodyType("application/json; charset=utf-8")
//                    .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36 Edg/109.0.1518.78")
//                    .setOnResponse((HttpResult res) -> {
//                    // 得到目标数据
//                    HttpResult.Body boy = res.getBody();
//                    Log.i("htpps",boy.toString());
//                    })
//                    .get();