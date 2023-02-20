package com.example.tiecodeluntan.glide;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.tiecodeluntan.R;

@SuppressWarnings("NonAsciiCharacters")
public class GlideTool {
    @SuppressLint("CheckResult")
    public static void 加载图片(ImageView 图片框, String 图片地址, String 加载图或颜色, String 错误图或颜色) {
        RequestBuilder<Drawable> g = Glide.with(图片框.getContext()).load(图片地址).thumbnail(0.5f).transition(DrawableTransitionOptions.withCrossFade()).override(318, 318);
        if (加载图或颜色 != null) g.placeholder(加载图或颜色.startsWith("#") ?
                new ColorDrawable(android.graphics.Color.parseColor(加载图或颜色)) :
                getDrawable(图片框.getContext(), 加载图或颜色));
        if (错误图或颜色 != null) g.error(错误图或颜色.startsWith("#") ?
                new ColorDrawable(android.graphics.Color.parseColor(错误图或颜色)) :
                getDrawable(图片框.getContext(), 错误图或颜色));
        g.apply(new RequestOptions().transforms(new CenterCrop()));
        g.into(图片框);
    }

    public static android.graphics.drawable.Drawable getDrawable(android.content.Context c, String s) {
        if (s.startsWith("/")) {
            return new android.graphics.drawable.BitmapDrawable(android.graphics.BitmapFactory.decodeFile(s));
        } else {
            return android.graphics.drawable.Drawable.createFromStream(getInputStream(c, s), s);
        }
    }

    public static java.io.InputStream getInputStream(android.content.Context c, String s) {
        java.io.InputStream is = null;
        try {
            is = c.getAssets().open(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return is;
    }

    private static android.app.Application instance = null;

    public static android.app.Application getApplication() {
        if (instance == null) {
            synchronized (android.app.Application.class) {
                if (instance == null) {
                    try {
                        instance = (android.app.Application) Class.forName("android.app.ActivityThread").
                                getDeclaredMethod("currentApplication").invoke(null);
                        if (instance != null) return instance;
                        Class activityThreadClass = Class.forName("android.app.ActivityThread");
                        Object localObject = activityThreadClass.getMethod("currentActivityThread", new Class[0]).invoke(null, (Object[]) null);
                        instance = (android.app.Application) activityThreadClass.getMethod("getApplication").invoke(localObject, (Object[]) null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return instance;
    }


}
