package com.example.webmusictest;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.show.api.ShowApiRequest;

import java.util.Date;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity{

    protected Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView txt = (TextView) this.findViewById(R.id.textView1);
        Button myBtn = (Button) this.findViewById(R.id.button1);
        myBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    @Override
                    public void run() {
                        String appid = "39525";
                        String secret = "7003fc66ab7443b3958e700508437360";
                        final String res = new ShowApiRequest("http://route.showapi.com/109-35"
                        , appid, secret)
                                .addTextPara("channelId", "")
                                .addTextPara("channelName", "")
                                .addTextPara("title", "")
                                .addTextPara("page", "")
                                .addTextPara("needContent", "")
                                .addTextPara("needHtml", "")
                                .addTextPara("needAllList", "")
                                .addTextPara("maxResult", "")
                                .post();
                        Log.i("Test", res);
                        mHandler.post(new Thread(){
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
