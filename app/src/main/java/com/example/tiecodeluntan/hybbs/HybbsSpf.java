package com.example.tiecodeluntan.hybbs;

import android.content.Context;
import android.content.SharedPreferences;

public class HybbsSpf {
    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;

    public static void 初始化(Context mContext,String Name){
        sp = mContext.getSharedPreferences(Name,Context.MODE_PRIVATE);
        editor = sp.edit();
    }
    public  static Boolean add(String key,String value){
        editor.putString(key,value);
        return editor.commit();
    }
    public  static String get(String key){
        return sp.getString(key,"");
    }
}
