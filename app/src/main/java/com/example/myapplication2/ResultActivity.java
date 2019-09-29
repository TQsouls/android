package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView nameTx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //找到文本id
        nameTx=findViewById(R.id.textName);

        //从intent中获取传递过来的值
        Intent intent = getIntent();
        if (intent != null)
        {
            //设置从intent中获取的值
            nameTx.setText(intent.getStringExtra("name"));
        }
    }
}
