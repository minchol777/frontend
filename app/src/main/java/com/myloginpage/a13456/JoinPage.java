package com.myloginpage.a13456;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class JoinPage extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joinpage);

    }

    public void bulletinboradstart(View view) {
        Log.e("클릭","클릭");
        Intent it = new Intent(getApplicationContext(),BulletinBorad.class);
        startActivity(it);
    }
}
