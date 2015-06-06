package com.avoscloud.beijing.push.demo.keepalive.appfordiabetes;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by linpan1 on 15/4/11.
 */
public class ForgetPwd extends Activity {
    EditText emailVerify;
    Button sendEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgetpwd);

        emailVerify = (EditText)findViewById(R.id.emailVerify);
        sendEmail = (Button)findViewById(R.id.sendEmail);

        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AVService.forgetPassword(emailVerify.getText().toString().trim());

            }
        });

    }
}
