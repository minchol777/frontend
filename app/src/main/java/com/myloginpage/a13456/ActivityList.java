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

import java.util.ArrayList;
import java.util.List;

public class ActivityList extends AppCompatActivity {

    ListView listView;
    Button regButton;
    String userId = "";
    ArrayList<String> titleList = new ArrayList<>();
    ArrayList<String> seqList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent it = getIntent();
        it.getStringExtra("it_tag");

        listView = findViewById(R.id.listview1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ActivityList.this, adapterView.getItemAtPosition(i) + " 클릭", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),DetailActivity1.class);
                intent.putExtra("board_seq", seqList.get(i));
                intent.putExtra("userid", userId);
                startActivity(intent);
            }
        });
        regButton = findViewById(R.id.reg_button);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityList.this, RegisterActivity1.class);
                intent.putExtra("userid", userId);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
//        List<String> titlelist = new ArrayList<>();
//        List<String> seq

        // 예시로 하드코딩하여 데이터 추가
        titleList.add("게시글 제목1");
        seqList.add("1");


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(ActivityList.this, android.R.layout.simple_list_item_1, titleList);
        listView.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
    }
}

