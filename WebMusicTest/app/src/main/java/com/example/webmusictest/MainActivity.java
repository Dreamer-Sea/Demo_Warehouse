package com.example.webmusictest;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.webmusictest.utils.ParseJsonWithGson;
import com.show.api.ShowApiRequest;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView txt = (TextView) findViewById(R.id.textview1);
        Button myBtn = (Button) findViewById(R.id.button1);
        myBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        String appid = "39525";
                        String secret = "7003fc66ab7443b3958e700508437360";
                        final String res = new ShowApiRequest("http://route.showapi.com/1071-1", appid, secret)
                                .post();
                        System.out.println(res);
                        ParseJsonWithGson.parseDailyNewsJson(res.toString());
                        mHandler.post(new Thread() {
                            @Override
                            public void run() {
                                txt.setText(res + " " + new Date());
                            }
                        });
                    }
                }.start();
            }
        });
    }
}
