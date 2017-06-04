package com.example.webmusictest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.webmusictest.beans.Person;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        responseText = (TextView) findViewById(R.id.response_text);
        Button sendRequest = (Button) findViewById(R.id.send_request);
        sendRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.send_request){
            sendRequestWithOkHttp();
        }
    }

    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://10.0.2.2/get_data.json")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    showResponse(responseData);
                    parseJSONWithGSON(responseData);
//                    parseJSONWithJSONObject(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithJSONObject(String jsonData){
        try{
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");

                JSONArray name = (JSONArray) jsonObject.get("name");
                String a = name.getJSONObject(0).getString("a");

                String version = jsonObject.getString("version");
                Log.d("MainActivity", id);
                Log.d("MainActivity", a);
                Log.d("MainActivity", version);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void parseJSONWithGSON(String jsonData){
        Gson gson = new Gson();
        List<Person> ps = gson.fromJson(jsonData, new TypeToken<List<Person>>(){}.getType());
        for (Person p : ps) {
            Log.d("MainActivity", p.getId());
            List<Person.Name> name = p.getName();
            for (Person.Name n : name) {
                Log.d("MainActivity", n.getA());
            }
            Log.d("MainActivity", p.getVersion());
        }
    }

    private void showResponse(final String respone){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                responseText.setText(respone);
            }
        });
    }
}
