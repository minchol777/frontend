package com.myloginpage.a13456;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BulletinBorad extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulletinborad);

    }

    public void listplay(View view) {
            String tag = view.getTag().toString();

            // 선택한 게시판에 따라 화면 전환 로직을 구현합니다.
            if (tag.equals("01")) {
                // 개인 게시판으로 화면 전환
                Intent intent = new Intent(getApplicationContext(), ActivityList.class);
                startActivity(intent);
            } else if (tag.equals("02")) {
                // 자유 게시판으로 화면 전환
                Intent intent = new Intent(getApplicationContext(), ActivityList3.class);
                startActivity(intent);
            } else if (tag.equals("03")) {
                // 학과 게시판으로 화면 전환
                Intent intent = new Intent(getApplicationContext(), ActivityList2.class);
                startActivity(intent);
            }
        }


}
