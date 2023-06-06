package com.myloginpage.a13456;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ActivityList2 extends AppCompatActivity {

    ListView listView;
    Button regButton;
    String userId = "";
    ArrayList<String> titleList = new ArrayList<>();
    ArrayList<String> seqList = new ArrayList<>();

    OkHttpClient client = new OkHttpClient();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list2);

        Intent it = getIntent();
        it.getStringExtra("it_tag");

        listView = findViewById(R.id.listview2);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ActivityList2.this, adapterView.getItemAtPosition(i) + " 클릭", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), DetailActivity2.class);
                intent.putExtra("board_seq", seqList.get(i));
                intent.putExtra("userid", userId);
                startActivity(intent);
            }
        });

        regButton = findViewById(R.id.reg_button2);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityList2.this, RegisterActivity2.class);
                intent.putExtra("userid", userId);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 예시로 하드코딩하여 데이터 추가
        titleList.add("게시글 제목1");
        seqList.add("1");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(ActivityList2.this, android.R.layout.simple_list_item_1, titleList);
        listView.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();

        // REST API 호출
        sendRestApiRequest();
    }

    private void sendRestApiRequest() {
        // REST API 엔드포인트 URL
        String url = "http://your-api-endpoint.com/api/resource";

        // 요청 생성
        Request request = new Request.Builder()
                .url(url)
                .build();

        // 요청 보내기
        try {
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                // 성공적인 응답 처리
                String responseData = response.body().string();
                // responseData를 원하는 방식으로 처리
            } else {
                // 응답 실패 처리
                // response.code()를 사용하여 HTTP 응답 상태 코드 확인
            }
        } catch (IOException e) {
            e.printStackTrace();
            // 예외 처리
        }
    }
}
