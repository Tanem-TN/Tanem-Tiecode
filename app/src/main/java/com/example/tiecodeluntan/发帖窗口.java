package com.example.tiecodeluntan;

import static com.zhihu.matisse.internal.utils.PathUtils.getPath;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.tiecodeluntan.GlobalClass.安卓窗口;
import com.google.android.material.card.MaterialCardView;
import com.widemouth.library.toolitem.WMToolImage;
import com.widemouth.library.wmview.WMTextEditor;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;


public class 发帖窗口 extends AppCompatActivity {
    WMTextEditor textEditor;
    MaterialCardView mCardView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fatiebuj);
        mCardView = findViewById(R.id.发布按钮);
        安卓窗口.申请所有权限(this, this);
        textEditor = findViewById(R.id.showTextEditor);
        mCardView.setOnClickListener(v -> {
            // 在这里添加单击事件处理逻辑
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == WMToolImage.ALBUM_CHOOSE && resultCode == RESULT_OK) {

        }
    }

}
