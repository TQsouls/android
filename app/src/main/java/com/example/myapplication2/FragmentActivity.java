package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication2.User.LoginFragment;
import com.example.myapplication2.User.RegisterFragment;

public class FragmentActivity extends AppCompatActivity {
    //声明变量按钮
    private Button loginBtn,registerBtn;

    //判断当前是否是登录按钮
    private boolean isLogin = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        //找到控件id
        loginBtn = findViewById(R.id.login);
        registerBtn = findViewById(R.id.register);

        //显示默认登录的Fragme
       getSupportFragmentManager().beginTransaction().replace(R.id.content,new LoginFragment()).commit();
    }
    public void login(View view){
        if (isLogin){
            return;
        }
        isLogin=true;
        getSupportFragmentManager().beginTransaction().replace(R.id.content,new LoginFragment()).commit();
        loginBtn.setBackgroundColor(Color.rgb(0,0xff,0xff));
        registerBtn.setBackgroundColor(Color.rgb(0,0xbb,0xbb));
    }

    public void register(View view){
        if (!isLogin){
            return;
        }
        isLogin=false;
        getSupportFragmentManager().beginTransaction().replace(R.id.content,new RegisterFragment()).commit();
        registerBtn.setBackgroundColor(Color.rgb(0,0xff,0xff));
        loginBtn.setBackgroundColor(Color.rgb(0,0xbb,0xbb));
    }
}
