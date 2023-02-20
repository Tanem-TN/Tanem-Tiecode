package com.example.tiecodeluntan.GlobalClass;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import androidx.appcompat.app.AppCompatActivity;

public class 安卓窗口 {
    public static void 切换窗口(Activity activity, Class<?> z) {
		Intent intent = new Intent(activity, z);
		activity.startActivity(intent);
    }

    public static void 申请所有权限(Context mContext, AppCompatActivity A){
        try {
            PackageManager mPackageMgr = mContext.getPackageManager();
            PackageInfo pack = mPackageMgr.getPackageInfo(mContext.getPackageName(), PackageManager.GET_PERMISSIONS);
            String[] permissions = pack.requestedPermissions;
            A.requestPermissions(permissions,1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
