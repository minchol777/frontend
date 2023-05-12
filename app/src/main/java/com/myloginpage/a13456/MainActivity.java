package com.myloginpage.a13456;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displaystart(View view) {
        Intent it = new Intent(getApplicationContext(),BulletinBorad.class);
        startActivity(it);
    }

    public void joinpagestart(View view) {
        Intent joinpage = new Intent(getApplicationContext(),JoinPage.class);
        startActivity(joinpage);
    }
}