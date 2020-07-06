package com.example.kb;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.Vector;

public class MainPage extends AppCompatActivity  {
        TextView textView;
    protected void onCreate(Bundle savedlnstanceState) {
        super.onCreate(savedlnstanceState);
        setContentView(R.layout.activity_main);

        tttt tt = new tttt();

        try {
            Log.d("tttt()  시작전","bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");

            tt.test();
            Log.d("tttt()  시작 후","bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
       } catch (IOException e) {
           e.printStackTrace();
        }

        realTime realTime = new realTime(); // 실시간 시간 출력해주는 클래스 뺴주기
        System.out.println("asdasdasd : "+  realTime);
        textView = (TextView) findViewById(R.id.curTime); // 텍스트뷰 id 찾기
        realTime.realTime(textView); // 객체얻어서 호출

        //----- 카카오맵 api 코드 시작
        MapView mapView = new MapView(this);
        RelativeLayout mapViewContainer = (RelativeLayout)findViewById(R.id.map_view);
        mapViewContainer.addView(mapView); //카카오맵 view 추가


        double[][] state = { //은행 위치
                {37.563234,126.972440},
                {37.564255, 126.974757},
                {37.569085, 126.976817},
                {37.569153, 126.978062},
                {37.570309, 126.982225}
        };

        String[] name ={  // 은행이름
                "국민은행 서소문지점",
                "국민은행 서소문로지점",
                "국민은행 태평로점",
                "국민은행 무교기점",
                "국민은행 서린동지점"
        };

        Vector<MapPoint> mapPoint = new Vector<>(); // 위도와 경도를 담을수 있는 벡터
        Vector<MapPOIItem> marker = new Vector<>(); // 위도와 경도 지도에 표시할 마커 벡터


        for(int i=0;i<state.length;i++){

            mapPoint.add(MapPoint.mapPointWithGeoCoord(state[i][0],state[i][1]));  //위경도 하나 추가
            marker.add(new MapPOIItem()); // 그것에대한 마커 추가
            marker.get(i).setItemName(name[i]); // 마커에 대한 이름 설정
            marker.get(i).setTag(i);
            marker.get(i).setMapPoint(mapPoint.get(i));

            if(i<2){ // 그냥 색깔 다르게 지정해보기 위해
               marker.get(i).setMarkerType(MapPOIItem.MarkerType.BluePin);
            }else if(i>=2 && i<4){
                marker.get(i).setMarkerType(MapPOIItem.MarkerType.YellowPin);
            }else{
                marker.get(i).setMarkerType(MapPOIItem.MarkerType.RedPin);
            }

            marker.get(i).setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커 클릭했을 때 빨간색으로
            mapView.addPOIItem(marker.get(i));   //mapView에 설정한 marker를 적용시킨다.

        }

         mapView.setMapCenterPoint(mapPoint.get(0), true); //mapPoint로 설정했던 변수와 true나 false 값을 넣은면 화면 중앙에 표시될 위치





    }
}