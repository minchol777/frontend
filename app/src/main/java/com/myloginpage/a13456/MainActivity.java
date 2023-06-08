package com.myloginpage.a13456;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.myloginpage.a13456.DTO.PostDTO;
import com.myloginpage.a13456.DTO.PostResponseDTO;
import com.myloginpage.a13456.PostInterface.PostApiService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    // 로그에 사용할 TAG 변수 선언
    final private String TAG = getClass().getSimpleName();

    // 사용할 컴포넌트 선언
    EditText userid_et, passwd_et;
    Button login_button;
    TextView join;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

// 사용할 컴포넌트 초기화
        userid_et = findViewById(R.id.email);
        passwd_et = findViewById(R.id.password);
        login_button = findViewById(R.id.button2);
        join = findViewById(R.id.join);

// 로그인 버튼 이벤트 추가
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.255.170:8080")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                PostApiService service = retrofit.create(PostApiService.class);

                int postId = 1; // the ID of the post you want to get

                Call<PostResponseDTO> call = service.getPost(postId);
                call.enqueue(new Callback<PostResponseDTO>() {
                    @Override
                    public void onResponse(Call<PostResponseDTO> call, Response<PostResponseDTO> response) {
                        if (response.isSuccessful()) {
                            PostResponseDTO post = response.body();
                            // Now you can use the information of the post.
                            // For example, you can display a message that includes the title of the post:
                            Toast.makeText(MainActivity.this,"Successfully got the post. The title of the post is " + post.getTitle() + ".", Toast.LENGTH_SHORT).show();
                        } else {
                            // If the response is not successful, there may be an error on the server side.
                            // You can display a message to the user or handle the error in other ways.
                            Toast.makeText(MainActivity.this,"Failed to get the post. The server responded with status code: " + response.code() + ".", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<PostResponseDTO> call, Throwable t) {
                        // This method is called if there is a failure during the communication with the server.
                        // For example, this may happen if the device has no internet connection.
                        Toast.makeText(MainActivity.this,"Failed to get the post. An error occurred during the communication with the server.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

// 조인 버튼 이벤트 추가
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, JoinPage.class);
                startActivity(intent);
            }
        });

    }




    }


