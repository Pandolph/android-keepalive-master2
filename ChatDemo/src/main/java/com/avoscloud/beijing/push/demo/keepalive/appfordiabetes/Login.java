package com.avoscloud.beijing.push.demo.keepalive.appfordiabetes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.SaveCallback;

public class Login extends ActionBarActivity {
    private Button loginin;
    private Button forgetpwd;
    private Button signup;
    Boolean loginFlag = false;
    EditText email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        loginin= (Button)findViewById(R.id.login_in);
        forgetpwd= (Button)findViewById(R.id.forget_psw);
        signup= (Button)findViewById(R.id.sign_up);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);

        // 保存 installation 到服务器
        AVInstallation.getCurrentInstallation().saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                AVInstallation.getCurrentInstallation().saveInBackground();
            }
        });
        AVService.initPushService(this);

        loginin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                AVService.login(email.getText().toString().trim(), password.getText().toString().trim(),
//                        loginFlag);
//
//                if (loginFlag == true) {
//                    Intent intent = new Intent();
//                    intent.setClass(Login.this, Tab.class);
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(Login.this, "登陆出错", Toast.LENGTH_LONG);
//                }
                if (email == null || password == null) {
                    Toast.makeText(Login.this, "空密码或邮箱", Toast.LENGTH_LONG).show();
                } else {
                    AVUser.logInInBackground(email.getText().toString().trim(),
                            password.getText().toString().trim(),
                            new LogInCallback<AVUser>() {
                                public void done(AVUser user, AVException e) {
                                    if (user != null) {

                                        Intent mainIntent = new Intent(Login.this, Tab.class);
                                        mainIntent.putExtra("email", email.getText().toString().trim());
                                        startActivity(mainIntent);

                                    } else {
                                        Toast.makeText(Login.this, "登陆失败，错误的密码或邮箱", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            }
        });

        forgetpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent();
                intent.setClass(Login.this, ForgetPwd.class);
                startActivity(intent);

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent();
                intent.setClass(Login.this, Signup.class);
                startActivity(intent);

            }
        });

    }



}
