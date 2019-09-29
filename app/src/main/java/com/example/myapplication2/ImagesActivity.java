package com.example.myapplication2;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ImagesActivity extends AppCompatActivity {

    private TextView tv;
    private static final int UPDATE = 0;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO 接收消息并且去更新UI线程上的控件内容
            if (msg.what == UPDATE) {
                // Bundle b = msg.getData();
                // tv.setText(b.getString("num"));
                tv.setText(String.valueOf(msg.obj));
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.images);


        tv =  findViewById(R.id.tv);
        new Thread() {
            @Override
            public void run() {
                // TODO 子线程中通过handler发送消息给handler接收，由handler去更新TextView的值
                try {
                    for (int i = 0; i < 1000; i++) {
                        Thread.sleep(1000);
                        Message msg = new Message();
                        msg.what = UPDATE;
                        // Bundle b = new Bundle();
                        // b.putString("num", "更新后的值：" + i);
                        // msg.setData(b);
                        msg.obj = "更新后的值：" + i;
                        handler.sendMessage(msg);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void play(View view){
        System.out.println("ok");
    }

}
