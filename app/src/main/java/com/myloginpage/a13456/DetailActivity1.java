package com.myloginpage.a13456;




import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.myloginpage.a13456.DTO.PostDTO;
import com.myloginpage.a13456.PostInterface.PostApiService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity1 extends AppCompatActivity {

    // 로그에 사용할 TAG
    final private String TAG = getClass().getSimpleName();

    // 사용할 컴포넌트 선언
    TextView title_tv, content_tv, date_tv;
    LinearLayout comment_layout;
    EditText comment_et;
    Button reg_button;

    // 선택한 게시물의 번호
    String board_seq = "";

    // 유저아이디 변수
    String userid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail1);

        // ListActivity 에서 넘긴 변수들을 받아줌
        board_seq = getIntent().getStringExtra("board_seq");
        userid = getIntent().getStringExtra("userid");

        // 컴포넌트 초기화
        title_tv = findViewById(R.id.title_tv);
        content_tv = findViewById(R.id.content_tv);
        date_tv = findViewById(R.id.date_tv);

        comment_layout = findViewById(R.id.comment_layout);
        comment_et = findViewById(R.id.comment_et);
        reg_button = findViewById(R.id.reg_button);

        // 등록하기 버튼을 눌렀을 때 댓글 등록 함수 호출
        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comment = comment_et.getText().toString();


                // 댓글 등록 함수 호출
               // registerComment(userid, comment, board_seq);
            }
        });

        // 해당 게시물의 데이터 불러오기
        initData();




    }

    private void initData() {
        // 해당 게시물의 데이터를 읽어오는 함수 호출
        loadBoardDetail(board_seq);
    }

    private void loadBoardDetail(String board_seq) {
        // 게시물의 상세 데이터를 불러오는 작업을 수행합니다.
        // 실제 서버와의 통신이 필요한 경우 서버로 데이터를 전송하고 결과를 처리하는 로직을 추가합니다.
        // 여기서는 예시로 간단히 데이터를 세팅하는 부분만 추가합니다.

        String title = "게시물 제목";
        String content = "게시물 내용";
        String date = "2023-05-12";

        // 받아온 데이터를 해당 뷰에 세팅합니다.
        title_tv.setText(title);
        content_tv.setText(content);
        date_tv.setText(date);

        // 해당 게시물에 대한 댓글 불러오는 함수 호출
        loadComments(board_seq);
    }

    private void loadComments(String board_seq) {
        // 댓글을 불러오는 작업을 수행합니다.
        // 실제 서버와의 통신이 필요한 경우 서버로 데이터를 전송하고 결과를 처리하는 로직을 추가합니다.
        // 여기서는 예시로 간단히 데이터를 세팅하는 부분만 추가합니다.

        // 댓글 데이터를 가정하여 예시로 작성합니다.
        JSONArray jsonArray = new JSONArray();
        try {
            JSONObject comment1 = new JSONObject();
            comment1.put("userid", "user1");
            comment1.put("content", "댓글 내용1");
            comment1.put("crt_dt", "2023-05-12");
            jsonArray.put(comment1);

            JSONObject comment2 = new JSONObject();
            comment2.put("userid", "user2");
            comment2.put("content", "댓글 내용2");
            comment2.put("crt_dt", "2023-05-13");
            jsonArray.put(comment2);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // 댓글 레이아웃 초기화
        comment_layout.removeAllViews();
    }}
