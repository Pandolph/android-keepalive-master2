package com.avoscloud.beijing.push.demo.keepalive.appfordiabetes;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.avos.avoscloud.AVOSCloud;

import org.json.JSONObject;

//推送并不是在这个java文件中实现，是在AVService中实现，这个文件只是用来以后调整的
public class CustomReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {
    try {

        JSONObject json = new JSONObject(intent.getExtras().getString("com.avos.avoscloud.Data"));
        final String message = json.getString("alert");
//        Intent resultIntent = new Intent(AVOSCloud.applicationContext, Tab.class);
//        PendingIntent pendingIntent =
//            PendingIntent.getActivity(AVOSCloud.applicationContext, 0, resultIntent,
//                PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mBuilder =
            new NotificationCompat.Builder(AVOSCloud.applicationContext)
                .setSmallIcon(R.drawable.abc_ic_menu_selectall_mtrl_alpha)
                .setContentTitle(
                    AVOSCloud.applicationContext.getResources().getString(R.string.app_name))
                .setContentText(message)
                .setTicker(message);
//        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setAutoCancel(true);

        int mNotificationId = 10086;
        NotificationManager mNotifyMgr =
            (NotificationManager) AVOSCloud.applicationContext
                .getSystemService(
                    Context.NOTIFICATION_SERVICE);
        mNotifyMgr.notify(mNotificationId, mBuilder.build());

    } catch (Exception e) {

    }
  }
}
