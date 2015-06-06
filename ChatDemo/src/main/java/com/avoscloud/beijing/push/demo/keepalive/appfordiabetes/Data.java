package com.avoscloud.beijing.push.demo.keepalive.appfordiabetes;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;

/**
 * Created by linpan1 on 15/3/25.
 */
public class Data extends Activity {
    private EditText height;
    private EditText diet;
    private EditText weight;
    private EditText medicine;
    private EditText exercise;
    private EditText bloodGlucose;

    private Button show;
    private Button operate;
    private Button myInformation;
    private Button chat;

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.data);

        height =(EditText)findViewById(R.id.height);
        diet  =(EditText)findViewById(R.id.diet);
        medicine =(EditText)findViewById(R.id.medicine);
        exercise =(EditText)findViewById(R.id.exercise);
        weight =(EditText)findViewById(R.id.weight);
        bloodGlucose =(EditText)findViewById(R.id.bloodGlucose);

//        Intent emailIntent = getIntent();
//        final String loginEmail = emailIntent.getStringExtra("email");

        AVUser currentUser = AVUser.getCurrentUser();
        final String loginName = currentUser.getUsername();
        final String loginEmail = currentUser.getEmail();

        Button saveBloodGlucose = (Button)findViewById(R.id.saveBloodGlucose);
        saveBloodGlucose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AVObject PatientDoc = new AVObject("BloodGlucose");
                PatientDoc.put("P_bloodGlucose", bloodGlucose.getText().toString().trim());
                PatientDoc.put("P_email", loginEmail);
                PatientDoc.put("P_name", loginName);
                PatientDoc.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(AVException e) {
                        if (e != null) System.out.println("保存不成功");
                    }
                });
                double num = Double.parseDouble(bloodGlucose.getText().toString().trim());
                //糖尿病血糖控制目标  良好 一般 不良  空腹血糖（mmol/L） 4.4－6.1 ≤7.0 ＞7.0
                // 餐后两小时血糖（mmol/L） 4.4－8.0 ≤10.0 ＞10.0
                if(num>8.0) Toast.makeText(Data.this, "请注意，当前血糖值过高", Toast.LENGTH_LONG).show();
                if(num<4.4) Toast.makeText(Data.this, "请注意，当前血糖值过低", Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.saveHeight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AVObject PatientDoc = new AVObject("Height");
                PatientDoc.put("P_height",height.getText().toString().trim());
                PatientDoc.put("P_email",loginEmail);
                PatientDoc.put("P_name",loginName);
                PatientDoc.saveInBackground();
            }
        });

        findViewById(R.id.saveDiet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AVObject PatientDoc = new AVObject("Diet");
                PatientDoc.put("P_diet",diet.getText().toString().trim());
                PatientDoc.put("P_email",loginEmail);
                PatientDoc.put("P_name",loginName);
                PatientDoc.saveInBackground();
            }
        });

        findViewById(R.id.saveWeight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AVObject PatientDoc = new AVObject("Weight");
                PatientDoc.put("P_weight",weight.getText().toString().trim());
                PatientDoc.put("P_email",loginEmail);
                PatientDoc.put("P_name",loginName);
                PatientDoc.saveInBackground();
            }
        });

        findViewById(R.id.saveExercise).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AVObject PatientDoc = new AVObject("Exercise");
                PatientDoc.put("P_exercise",exercise.getText().toString().trim());
                PatientDoc.put("P_email",loginEmail);
                PatientDoc.put("P_name",loginName);
                PatientDoc.saveInBackground();
            }
        });

        findViewById(R.id.saveMedicine).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AVObject PatientDoc = new AVObject("Medicine");
                PatientDoc.put("P_medicine",medicine.getText().toString().trim());
                PatientDoc.put("P_email",loginEmail);
                PatientDoc.put("P_name",loginName);
                PatientDoc.saveInBackground();
            }
        });

//        show = (Button)findViewById(R.id.show);
//        operate = (Button)findViewById(R.id.operate);
//        myInformation = (Button)findViewById(R.id.myinformation);
//        chat = (Button)findViewById(R.id.chat);

//        show.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String xuetang_string=xuetang.getText().toString().trim();
//                String yinshi_string=yinshi.getText().toString().trim();
//                String email_string=email.getText().toString().trim();
//                String height_string=height.getText().toString().trim();
//                String yongyao_string=yongyao.getText().toString().trim();
//                String name_string=name.getText().toString().trim();
//                String weight_string=weight.getText().toString().trim();
//                String yundong_string=yundong.getText().toString().trim();
//                AVService.savePatientDoc(weight_string,email_string,name_string,xuetang_string,
//                        yinshi_string ,yongyao_string,yundong_string,height_string, true);
//
//            }
//        });


//        show.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(Data.this, Figure.class);
//                startActivity(intent);
//            }
//        });
//
//        operate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(Data.this, SearchAndDelete.class);
//                startActivity(intent);
//            }
//        });
//
//        myInformation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(Data.this, SearchAndDelete.class);
//                startActivity(intent);
//            }
//        });
//
//        chat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(Data.this, SearchAndDelete.class);
//                startActivity(intent);
//            }
//        });

    }
}
