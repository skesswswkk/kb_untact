package com.example.kb;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Date;

public class realTime extends AppCompatActivity {
    public void realTime(final TextView curTime) {

        final Handler handler = new Handler() // 실시간으로 데이터받아서 처리해주기 위해 핸들러
            //쓰레드에서 처리하는 내용을 받는다
        {
            @Override
            public void handleMessage(Message msg) {
                curTime.setText("현재시간 : " + DateFormat.getDateTimeInstance().format(new Date()));
            }
        };
        Runnable run = new Runnable() {
            @Override
            public void run() {
                    while(true){
                        try{
                            Thread.sleep(1000);
                        }catch (InterruptedException e){}
                        handler.sendEmptyMessage(1); //핸드러를 호출한다. 즉 시간을 최신화
                    }
                }
            };
            Thread thread = new Thread(run);
            thread.start();
        }
    }
