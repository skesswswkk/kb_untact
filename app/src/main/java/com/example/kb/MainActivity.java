package com.example.kb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
     // 인트로 화면



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler hd = new Handler();
        hd.postDelayed(new splashhandler(),3000);// 1초 후에 hd handler 실행 3초
    }

    private class splashhandler implements Runnable{
        public void run(){
            startActivity(new Intent(getApplication(),MainPage.class)); //로딩이 끝날 후 이동할 페이지
            MainActivity.this.finish(); //로딩페이지 Activity statck 에서 제거
        }
    }

    public void onBackPressed(){
        //초반 플래시 화면 넘어갈 때 뒤로가기 버튼 못 누르게 하는 함수
    }
}