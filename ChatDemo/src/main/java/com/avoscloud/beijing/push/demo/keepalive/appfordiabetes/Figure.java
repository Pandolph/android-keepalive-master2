package com.avoscloud.beijing.push.demo.keepalive.appfordiabetes;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linpan1 on 15/3/25.
 */
public class Figure extends Activity {
    int count;
    ArrayList<String> BGArray= new ArrayList<String>();
    private SurfaceHolder surfaceHolder;
    private Paint paint;
    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.figure);
        paint = new Paint();
        SurfaceView surfaceView = (SurfaceView)findViewById(R.id.surfaceView);

        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
  //              drawBack(holder);
                Canvas canvas = holder.lockCanvas();
                paint.setStrokeWidth(20);
                paint.setColor(20);
                Bitmap back = BitmapFactory.decodeResource(Figure.this.getResources(), R.drawable.exercise);
                canvas.drawBitmap(back, 0, 0, null);
                for (int i =1; i<BGArray.size();i++){
                    canvas.drawPoint(10*i, Integer.parseInt(BGArray.get(i)),paint);
                    canvas.drawLine(10*i-10, Integer.parseInt(BGArray.get(i - 1)),
                            10*i, Integer.parseInt(BGArray.get(i)),paint);
                }


                holder.unlockCanvasAndPost(canvas);


            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });

        AVUser currentuser = AVUser.getCurrentUser();
        String patientEmail=currentuser.getEmail();
        AVQuery<AVObject> query = new AVQuery<AVObject>("BloodGlucose");
        query.whereEqualTo("P_email", patientEmail);
        query.findInBackground(new FindCallback<AVObject>() {
            public void done(List<AVObject> avObjects, AVException e) {
                if (e == null) {
                    Log.d("成功", "查询到" + avObjects.size() + " 条符合条件的数据");
                    Toast.makeText(Figure.this, "查询到" + avObjects.size() + " 条符合条件的数据", Toast.LENGTH_LONG).show();
                    for (count = 0; count < avObjects.size(); count++) {
                        BGArray.add(avObjects.get(count).getString("P_bloodGlucose"));
                    }
                } else {
                    Log.d("失败", "查询错误: " + e.getMessage());
                }
            }
        });

    }
    private  void drawBack(SurfaceHolder holder){

        Canvas canvas = holder.lockCanvas();

//绘制白色背景

        canvas.drawColor(Color.WHITE);

        Paint p = new Paint();

        p.setColor(Color.BLACK);

        p.setStrokeWidth(2);

//绘制坐标轴

        canvas.drawLine(0, 20, 100, 20, p);


        holder.unlockCanvasAndPost(canvas);

        holder.lockCanvas(new Rect(0, 0, 0, 0));

        holder.unlockCanvasAndPost(canvas);

    }
}
