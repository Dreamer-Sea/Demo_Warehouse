package com.example.webmusictest;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.webmusictest.threads.ReceiveXML;
import com.example.webmusictest.utils.ParseJsonWithGson;
import com.show.api.ShowApiRequest;

import java.util.Date;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }
}
