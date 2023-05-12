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
        int id = view.getId();

        LinearLayout linearLayout = findViewById(id);
        String tag = (String) linearLayout.getTag();

        Intent it = new Intent(getApplicationContext(),ActivityList.class);
        it.putExtra("it_tag",tag);
        startActivity(it);
    }
}
