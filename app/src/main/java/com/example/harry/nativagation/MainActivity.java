package com.example.harry.nativagation;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    EditText edititle,edicon;
    private TextView textView;
    private Button button;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            textView.setText("消息处理.....");
        }
    };
    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView) findViewById(R.id.txt);
        button=(Button) findViewById(R.id.button);
        edititle=(EditText) findViewById(R.id.edititle);
        edicon=(EditText) findViewById(R.id.edicon);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addNotification();
            }
        });


        ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);

        ShortcutInfo shortcutInfo = new ShortcutInfo.Builder(this,"01")
                .setShortLabel("trinea.cn")
                .setLongLabel("Open trinea.cn")
                .setDisabledMessage("Disabled")
                .setIntent(new Intent("MAIN2"))
                .setIcon(Icon.createWithResource(this,R.drawable.ic_perm_contact_calendar_black_24dp))
                .build();


        shortcutManager.setDynamicShortcuts(Arrays.asList(shortcutInfo));


        new Thread(new Runnable() {
           @Override
           public void run() {
                SystemClock.sleep(3000);
               handler.sendMessage(new Message());
           }
       }).start();

    }

    private void addNotification() {
        Notification notificationCompat = new NotificationCompat.Builder(getApplicationContext())
                .setContentText(edicon.getText().toString())
                .setContentTitle(edititle.getText().toString())
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setChannelId("01")
                .build();

        NotificationManager nu = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel channelLove = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            channelLove = new NotificationChannel(
                    "01",
                    "Channel Love",
                    NotificationManager.IMPORTANCE_HIGH);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            nu.createNotificationChannel(channelLove);
        }
        nu.notify(0,notificationCompat);
    }
}
