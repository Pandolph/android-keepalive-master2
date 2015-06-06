package com.avoscloud.beijing.push.demo.keepalive.appfordiabetes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.avos.avoscloud.AVUser;

/**
 * Created by linpan1 on 15/4/28.
 */
public class MyInformation extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myinformation);
        AVUser currentUser = AVUser.getCurrentUser();
        String name = currentUser.getUsername();
        String email = currentUser.getEmail();
        String sex = currentUser.getString("sex");
        String birthday = currentUser.getString("birthday");

        EditText myBirthday = (EditText)findViewById(R.id.myBirthday);
        EditText myEmail = (EditText)findViewById(R.id.myEmail);
        EditText myName = (EditText)findViewById(R.id.myName);
        EditText mySex = (EditText)findViewById(R.id.mySex);

        myBirthday.setText(birthday);
        mySex.setText(sex);
        myEmail.setText(email);
        myName.setText(name);
        findViewById(R.id.Figure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MyInformation.this,Figure.class);
                startActivity(intent);
            }
        });
    }
}
