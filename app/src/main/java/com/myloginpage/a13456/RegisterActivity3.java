package com.myloginpage.a13456;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

public class RegisterActivity3 extends AppCompatActivity {
    EditText title_et, content_et;
    Button reg_button;
    String userid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);

        userid = getIntent().getStringExtra("userid");

        title_et = findViewById(R.id.title_et);
        content_et = findViewById(R.id.content_et);
        reg_button = findViewById(R.id.reg_button);

        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = title_et.getText().toString();
                String content = content_et.getText().toString();

                // 게시물 등록 함수 호출
                registerBoard(userid, title, content);
            }
        });
    }

    private void registerBoard(String userid, String title, String content) {
        try {
            // 파일명은 게시물 제목으로 지정하고, 내용은 게시물 내용으로 지정하여 파일에 저장합니다.
            String filename = title + ".txt";
            String data = content;

            FileOutputStream fos = openFileOutput(filename, MODE_PRIVATE);
            fos.write(data.getBytes());
            fos.close();

            Toast.makeText(RegisterActivity3.this, "등록되었습니다.", Toast.LENGTH_SHORT).show();
            finish();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(RegisterActivity3.this, "등록 실패", Toast.LENGTH_SHORT).show();
        }
    }
}