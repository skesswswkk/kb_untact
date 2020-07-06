package com.example.kb;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;


import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClient;
import com.amazonaws.services.rekognition.model.DetectLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectLabelsResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.Label;
import com.amazonaws.util.IOUtils;

class tttt{
    public void test() throws FileNotFoundException, IOException {
        // 로컬 파일 경로
        Log.d("tttt()  메소드 호출됨 ","bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        Log.d("tttt()  파읽읽는다.","bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");

        Log.d("어딜까 여긴??;; : " ,filePath);

        // 인풋스트림에 파일 추가
        ByteBuffer imageBytes;
        try (InputStream inputStream = new FileInputStream(new File(filePath))) {
            imageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
        }

        // AmazonRekognition - Amazon Rekognition에 액세스하기위한 인터페이스
        // AmazonRekognitionClient aws 계정 연동을 위한
        AmazonRekognition rekognitionClient = new AmazonRekognitionClient(
                new BasicAWSCredentials("", ""));

        // AmazonWebServiceRequest를 상속 받고 있음
        DetectLabelsRequest request = new DetectLabelsRequest()
                .withImage(new Image() // 입력 이미지는 base64로 인코딩 된 바이트 또는 S3 객체입니다
                        .withBytes(imageBytes))
                .withMaxLabels(10) // 서비스에서 응답으로 반환 할 최대 레이블 수입니다.
                .withMinConfidence(77F); // 레이블이 반환 할 최소 정확도 수준을 지정합니다.

        try {
            // AmazonWebServiceResult<ResponseMetadata> 상속 받고 있음
// 입력으로 제공된 이미지 (JPEG 또는 PNG) 내의 실제 엔티티 인스턴스를 감지
            DetectLabelsResult result = rekognitionClient.detectLabels(request);
            List <Label> labels = result.getLabels(); // 레이블 뽑기

            Log.d("파일 위치 : ",  filePath);
            for (Label label: labels) {
                // getName 은 레이블 즉 객체 추출한 명 , getConfidence().toString()은 레이블의 정확도
               Log.d(label.getName() + ": ", label.getConfidence().toString());
            }

        } catch (Exception e) {
            System.out.println("에러에러ㅔㅇ러ㅔ어레어레어레어레ㅓㅔ어ㅔㄹ어ㅔ러ㅔ");
            e.printStackTrace();
        }
    }
}