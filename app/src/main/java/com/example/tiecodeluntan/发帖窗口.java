package com.example.tiecodeluntan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.tiecodeluntan.GlobalClass.安卓窗口;
import com.google.android.material.card.MaterialCardView;
import com.widemouth.library.toolitem.WMToolImage;
import com.widemouth.library.wmview.WMTextEditor;


public class 发帖窗口 extends AppCompatActivity {
    WMTextEditor textEditor;
    MaterialCardView mCardView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fatiebuj);
        mCardView = findViewById(R.id.发布按钮);
        安卓窗口.申请所有权限(this,this);
        textEditor = findViewById(R.id.showTextEditor);
        mCardView.setOnClickListener(v -> {
            // 在这里添加单击事件处理逻辑
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == WMToolImage.ALBUM_CHOOSE && resultCode == RESULT_OK) {
//            textEditor.onActivityResult2("http://101.33.227.148/upload/userfile/1/tmp/e94ce3076bad9ecf89b8e81469009b9e/1ba952094a0c8b74174a66b043b21b31.png");
            textEditor.onActivityResult(data);
            textEditor.getHtml();
        }
    }

}
