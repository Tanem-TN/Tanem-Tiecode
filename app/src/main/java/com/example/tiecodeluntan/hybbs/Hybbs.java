package com.example.tiecodeluntan.hybbs;

import android.content.Context;

import java.util.concurrent.CountDownLatch;

import cn.zhxu.okhttps.HTTP;
import cn.zhxu.okhttps.HttpResult;

public class Hybbs {

    static String url = "http://101.33.227.148/";
    static final  String[] prompt = {"两次密码不一致","账号已经存在!","邮箱已经存在!","邮箱验证码已经过期,请获取新邮箱验证码!","Email 格式不对","账号注册成功!"};

    public Hybbs(Context mContext) {
        HybbsSpf.初始化(mContext, "HYBBS");
    }

    public static String login(String user,String email,String pass1,String pass2,String codec) {
        final CountDownLatch latch = new CountDownLatch(1);
        final String[] cc = {"nn"};
        HTTP okhttp = HTTP.builder().build();
        okhttp.async(url + "?user/add.html")
                .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36 Edg/111.0.0.0")
                .addHeader("Referer","http://101.33.227.148/?user/add.html")
                .addHeader("Cookie", "X_CACHE_KEY=7d13f6e7ce4bacd92d1b52bd8b4bf229; PHPSESSID=fpogdfttvm1q7tj68dfiftkl0a; re_url=http%3A%2F%2F101.33.227.148%2F%3Fadmin.html; fba2c8375c2563928ab862744d4f2a54=7facdc08-db13-4e6b-88fb-4fb2b841f1bc.8SZ_1YGMV_TeDkY7FXgburX_qfU; order=id%20desc; serverType=nginx; sites_path=/www/wwwroot; site_model=php; rank=list; file_recycle_status=true; Path=/www/wwwroot/hybbs/Plugin/hy_email_check; HY_EMAILA=ofibaBDPrs1AMMp7; HY_EMAILT=xz-b5dh9v%40tempmail.cn")
                .addBodyPara("user",user)
                .addBodyPara("email",email)
                .addBodyPara("pass1",pass1)
                .addBodyPara("pass2",pass2)
                .addBodyPara("codec",codec)
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
        if (cc[0].contains(prompt[0])) {
            return prompt[0];
        } else if (cc[0].contains(prompt[1])) {
            return prompt[1];

        } else if (cc[0].contains(prompt[2])) {
            return prompt[2];

        } else if (cc[0].contains(prompt[3])) {
            return prompt[3];
        } else if (cc[0].contains(prompt[4])) {
            return prompt[4];
        } else{
            return prompt[5];
        }


    }

}
