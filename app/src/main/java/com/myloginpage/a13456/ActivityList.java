package com.myloginpage.a13456;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.myloginpage.a13456.DetailActivity1;
import com.myloginpage.a13456.R;
import com.myloginpage.a13456.RegisterActivity1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ActivityList extends AppCompatActivity {

    ListView listView;
    Button regButton;
    String userId = "";
    ArrayList<String> titleList = new ArrayList<>();
    ArrayList<String> seqList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent it = getIntent();
        userId = it.getStringExtra("userid");

        listView = findViewById(R.id.listview1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ActivityList.this, adapterView.getItemAtPosition(i) + " 클릭", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), DetailActivity1.class);
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
        // 서버에서 게시글 목록을 가져오는 요청 보내기
        GetBoardListTask getBoardListTask = new GetBoardListTask();
        getBoardListTask.execute();
    }

    class GetBoardListTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            String serverUrl = "http://15.164.252.136/boardlist.php";

            try {
                URL url = new URL(serverUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(15000);
                conn.setReadTimeout(15000);
                conn.setDoOutput(false);

                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                } else {
                    Log.e("GetBoardListTask", "HTTP Error Code: " + responseCode);
                }
            } catch (Exception e) {
                Log.e("GetBoardListTask", "Error: " + e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            if (response != null) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    titleList.clear();
                    seqList.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String title = jsonObject.getString("title");
                        String seq = jsonObject.getString("seq");
                        titleList.add(title);
                        seqList.add(seq);
                    }

                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(ActivityList.this, android.R.layout.simple_list_item_1, titleList);
                    listView.setAdapter(arrayAdapter);
                    arrayAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    Log.e("GetBoardListTask", "JSON Exception: " + e.getMessage());
                }
            } else {
                Toast.makeText(ActivityList.this, "서버로부터 데이터를 가져오지 못했습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

