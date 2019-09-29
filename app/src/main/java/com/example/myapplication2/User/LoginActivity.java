package com.example.myapplication2.User;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication2.DB.UserDB;
import com.example.myapplication2.R;
import com.example.myapplication2.ScendActivity;

public class LoginActivity extends AppCompatActivity {
    //初始化用户名和密码
     private String userName;
     private String userPsd;

     //初始化Sharedpreferences 控件
     private SharedPreferences loginPreferences;
     private SharedPreferences.Editor loginEditor;

     //初始化CheckBox的状态
     private boolean isSavePsd;
     private boolean isAutoLogin;

     private String a;
     private String b;


    //初始化两个CheckBox按钮
     private CheckBox checkLogin;
     private  CheckBox checkPsd;

     //初始化两个文本款的名字
     private EditText nameText;
     private EditText psdText;

    //初始化数据库建表
    private UserDB userDB;
    private SQLiteDatabase uDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        loginPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);

        isSavePsd  = loginPreferences.getBoolean("isSavePsd",false);
        isAutoLogin = loginPreferences.getBoolean("isAutoLogin",false);

        userName = loginPreferences.getString("name",null);
        userPsd = loginPreferences.getString("psd",null);

        //指定数据库名字
        userDB = new UserDB(this,"UserInfo.db",null,1);
        uDB = userDB.getReadableDatabase();

        if(isAutoLogin)
        {
           loadWelcome();
        }
       else
           {
             loadlogin();
           }
    }

    //调用登录界面
    private void loadlogin() {

        setContentView(R.layout.activity_login);

         nameText =findViewById(R.id.editText1);
         psdText=findViewById(R.id.editText2);

        //自动登录
         checkLogin = findViewById(R.id.checkLogin);
        //记住密码
         checkPsd = findViewById(R.id.checkPwd);

        if (isSavePsd){
            nameText.setText(userName);
            psdText.setText(userPsd);

            checkPsd.setChecked(true);
        }
        checkLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheckd) {
                if (isCheckd){
                    checkPsd.setChecked(true);
                }
            }
        });
    }

    //调用欢迎界面
    private void loadWelcome() {
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this, ScendActivity.class);
        intent.putExtra("userName",a);
        startActivity(intent);
        LoginActivity.this.finish();

    }

    //当界面退出时销毁
    protected void onDestroy() {
        if (uDB!=null){
            userDB.close();
            uDB.close();
        }
        super.onDestroy();
    }

    //登录按钮
    public void Login(View view){

        EditText editText =findViewById(R.id.editText1);
        EditText editText1=findViewById(R.id.editText2);

        a = editText.getText().toString();
        b = editText1.getText().toString();
        Cursor cursor = uDB.rawQuery("select * from UserInfo where User_id=?",new String[]{
                a });
        Cursor pwd = uDB.rawQuery("select * from UserInfo where pwd=?", new String[]{
                b });
        if (cursor.getCount() > 0) {

            if (pwd.moveToFirst()) {

                loginEditor = loginPreferences.edit();

                userName = nameText.getText().toString();
                userPsd = psdText.getText().toString();

                loginEditor.putString("name", userName);
                loginEditor.putString("psd", userPsd);

                loginEditor.putBoolean("isSavePsd", checkPsd.isChecked());
                loginEditor.putBoolean("isAutoLogin", checkLogin.isChecked());

                loginEditor.commit();
                loadWelcome();

            }else {
                Toast.makeText(this,"密码不正确！",Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "用户名不存在！", Toast.LENGTH_SHORT).show();
        }
    }


    //清空按钮
    public void remove(View view){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

}
