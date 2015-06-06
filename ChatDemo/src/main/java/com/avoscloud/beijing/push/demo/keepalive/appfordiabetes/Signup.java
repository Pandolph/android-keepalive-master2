package com.avoscloud.beijing.push.demo.keepalive.appfordiabetes;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by linpan1 on 15/4/15.
 */
public class Signup extends Activity {

    EditText signupName;
    EditText signupEmail;
    EditText signupPassword;
    RadioGroup signupSex;
    DatePicker signupBirthday;
    Button signupButton;
    String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        signupName =(EditText)findViewById(R.id.sign_up_name);
        signupEmail =(EditText)findViewById(R.id.sign_up_email);
        signupPassword =(EditText)findViewById(R.id.sign_up_password);
        signupButton = (Button)findViewById(R.id.sign_up_button);
        signupSex = (RadioGroup)findViewById(R.id.sign_up_sex);
        signupBirthday = (DatePicker)findViewById(R.id.sign_up_birthday);


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = signupBirthday.getYear();
                int month =signupBirthday.getMonth();
                int day = signupBirthday.getDayOfMonth();
                String yearString = year +"";
                String monthString = month +"";
                String dayString = day +"";
                String birthday = yearString+monthString+dayString;
                AVService.signup(signupName.getText().toString().trim(), signupEmail.getText().toString().trim(),
                        signupPassword.getText().toString().trim(),sex,birthday );
                Toast.makeText(Signup.this, "注册成功", Toast.LENGTH_LONG).show();
            }
        });

        signupSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.sign_up_male){
                    sex = "male";
                }else{
                    sex = "female";
                }
            }
        });



    }
}
