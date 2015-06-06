package com.avoscloud.beijing.push.demo.keepalive.appfordiabetes;

import android.content.Context;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.PushService;
import com.avos.avoscloud.RequestEmailVerifyCallback;
import com.avos.avoscloud.RequestPasswordResetCallback;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.SignUpCallback;

/**
 * Created by linpan1 on 15/4/2.
 */
public class AVService {

    final Boolean flag= false;

    public static void savePatientDoc(String P_weight,String P_email,String P_name,String P_xuetang,
                                      String P_yinshi,String P_yongyao,String P_yundong,
                                      String P_height, boolean flag){
        String docOrGoal =null;

        if(flag==true) docOrGoal= "PatientDoc";
        else docOrGoal= "PatientGoal";

        AVObject PatientDoc = new AVObject(docOrGoal);
        PatientDoc.put("P_weight",P_weight);
        PatientDoc.put("P_email",P_email);
        PatientDoc.put("P_name",P_name);
        PatientDoc.put("P_xuetang",P_xuetang);
        PatientDoc.put("P_yinshi",P_yinshi);
        PatientDoc.put("P_yongyao",P_yongyao);
        PatientDoc.put("P_yundong",P_yundong);
        PatientDoc.put("P_height",P_height);
        PatientDoc.saveInBackground(new SaveCallback() {
            public void done(AVException e) {
                if (e == null) {
                    // 保存成功
                } else {
                    // 保存失败，输出错误信息
                }
            }
        });
    }

    public static void signup(String name, String email, String password,String sex,String birthday){
        AVUser user = new AVUser();
        user.setUsername(name);
        user.setEmail(email);
        user.setPassword(password);
        user.put("sex", sex);
        user.put("birthday",birthday);

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {

                    System.out.println("注册成功");
                } else {
                    System.out.println("注册不成功");
                }
            }
        });
        user.requestEmailVerfiyInBackground(email, new RequestEmailVerifyCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {

                    System.out.println("发送确认邮件成功");
                } else {
                    System.out.println("发送确认邮件不成功");
                }
            }
        });
    }

    public static void login(String email, String password, Boolean flag){
        final boolean flaginner = flag;
        AVUser.logInInBackground(email, password, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                if (avUser != null) {
//                    flaginner = true;
//                    Intent intent=new Intent();
//                    intent.setClass(this.this, Tab.class);
//                    this.startActivity(intent);
                    System.out.println("登陆成功");
                } else {
//                    flaginner = false;
                    System.out.println("登陆不成功");
                }
            }
        });
        flag=flaginner;
    }

    public static boolean forgetPassword(String email){
        AVUser user = new AVUser();

        user.requestPasswordResetInBackground(email, new RequestPasswordResetCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    System.out.println("已发送");

                } else {
                    System.out.println("发送出错");
                }
            }
        });
        user.requestEmailVerfiyInBackground(email, new RequestEmailVerifyCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {

                    System.out.println("发送确认邮件成功");
                } else {
                    System.out.println("发送确认邮件不成功");
                }
            }
        });
        return true;
    }

    public static void initPushService(Context ctx) {
        PushService.setDefaultPushCallback(ctx, Login.class);
        PushService.subscribe(ctx, "public", Login.class);
        AVInstallation.getCurrentInstallation().saveInBackground();
    }



}
