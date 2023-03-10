package com.example.tiecodeluntan.hybbs;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.util.concurrent.CountDownLatch;

import cn.zhxu.okhttps.HTTP;
import cn.zhxu.okhttps.HttpResult;

public class Hybbs {

    static String url = "http://101.33.227.148/";
    static final String[] adduserprompt = {"两次密码不一致", "账号已经存在!", "邮箱已经存在!", "邮箱验证码已经过期,请获取新邮箱验证码!", "Email 格式不对", "账号注册成功!"};
    static final String[] loginprompt = {"账号不存在!", "密码错误!", "登录成功!"};

    public Hybbs(Context mContext) {

    }

    //注册
    public static String adduser(String user, String email, String pass1, String pass2, String codec) {
        final CountDownLatch latch = new CountDownLatch(1);
        final String[] cc = {""};
        HTTP okhttp = HTTP.builder().build();
        okhttp.async(url + "?user/add.html")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36 Edg/111.0.0.0")
                .addHeader("Referer", "http://101.33.227.148/?user/add.html")
                .addHeader("Cookie", "X_CACHE_KEY=7d13f6e7ce4bacd92d1b52bd8b4bf229; PHPSESSID=fpogdfttvm1q7tj68dfiftkl0a; re_url=http%3A%2F%2F101.33.227.148%2F%3Fadmin.html; fba2c8375c2563928ab862744d4f2a54=7facdc08-db13-4e6b-88fb-4fb2b841f1bc.8SZ_1YGMV_TeDkY7FXgburX_qfU; order=id%20desc; serverType=nginx; sites_path=/www/wwwroot; site_model=php; rank=list; file_recycle_status=true; Path=/www/wwwroot/hybbs/Plugin/hy_email_check; HY_EMAILA=ofibaBDPrs1AMMp7; HY_EMAILT=xz-b5dh9v%40tempmail.cn")
                .addBodyPara("user", user)
                .addBodyPara("email", email)
                .addBodyPara("pass1", pass1)
                .addBodyPara("pass2", pass2)
                .addBodyPara("codec", codec)
                .setOnResponse((HttpResult res) -> {
                    HttpResult.Body boy = res.getBody();
                    cc[0] = boy.toString();
                    latch.countDown();
                }).post();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (cc[0].contains(adduserprompt[0])) {
            return adduserprompt[0];
        } else if (cc[0].contains(adduserprompt[1])) {
            return adduserprompt[1];

        } else if (cc[0].contains(adduserprompt[2])) {
            return adduserprompt[2];

        } else if (cc[0].contains(adduserprompt[3])) {
            return adduserprompt[3];
        } else if (cc[0].contains(adduserprompt[4])) {
            return adduserprompt[4];
        } else {
            return adduserprompt[5];
        }


    }

    //登录
    public static String login(String user, String pass) {
        final CountDownLatch latch = new CountDownLatch(1);
        String[] cc = {""};
        final String[] Cookie = {""};
        HTTP okhttp = HTTP.builder().build();
        okhttp.async(url + "?user/login.html")
                .addBodyPara("user", user)
                .addBodyPara("pass", pass)
                .setOnResponse((HttpResult res) -> {
                    HttpResult.Body boy = res.getBody();
                    cc[0] = boy.toString();
                    Cookie[0] = res.getHeaders("Set-Cookie").get(0);
                    latch.countDown();
                }).post();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (cc[0].contains(loginprompt[0])) {
            return loginprompt[0];
        } else if (cc[0].contains(loginprompt[1])) {
            return loginprompt[1];
        } else {
            HybbsSpf.add("Cookie", Cookie[0]);
            return cc[0];
        }
    }

    //判断用户是否投票
    public static String recode(String pid) {
        final CountDownLatch latch = new CountDownLatch(1);
        final String[] cc = {""};
        HTTP okhttp = HTTP.builder()
                .build();
        okhttp.async(url + "api/codec")
                .addHeader("Cookie", HybbsSpf.get("Cookie"))
                .addBodyPara("pid", pid)
                .setOnResponse((HttpResult res) -> {
                    HttpResult.Body boy = res.getBody();
                    cc[0] = boy.toString();
                    latch.countDown();
                }).post();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return cc[0];
    }

    //文件上传
    public static String postimg(String url) {
        final CountDownLatch latch = new CountDownLatch(1);
        final String[] cc = {""};
        HTTP okhttp = HTTP.builder()
                .build();
        okhttp.async(url + "?post/upload.html")
                .addHeader("Connection", "Keep-Alive")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Accept", "application/json, text/javascript, */*; q=0.01")
                .addHeader("Accept-Encoding", "gzip, deflate")
                .addHeader("Cookie", HybbsSpf.get("Cookie"))
                .addFilePara("photo", url)
                .setOnResponse((HttpResult res) -> {
                    HttpResult.Body boy = res.getBody();
                    cc[0] = boy.toString();
                    latch.countDown();
                }).post();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return cc[0];
    }


}
