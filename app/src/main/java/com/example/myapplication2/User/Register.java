package com.example.myapplication2.User;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.myapplication2.DB.UserDB;
import com.example.myapplication2.R;

public class Register extends AppCompatActivity {

    //初始化标签
    private RadioButton maleBtn,emaleBtn;
    private EditText name,pwd,checked,city;
    private  String male = "男";


    //初始化数据库建表
    private UserDB userDB;
    private SQLiteDatabase uDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //指定数据库名字
        userDB = new UserDB(this,"UserInfo.db",null,1);
        uDB = userDB.getReadableDatabase();

        setContentView(R.layout.activity_register);

        //找到对应的控件的id
        name = findViewById(R.id.name);
        pwd = findViewById(R.id.pwd);
        checked = findViewById(R.id.check);
        city = findViewById(R.id.place);
        maleBtn = findViewById(R.id.male);


        if (!maleBtn.isChecked()){
            male="女";
        }
    }

    //注册按钮单击事件开始
    public void register(View view){

        //调用CheckInfo函数  根据返回值给出提示信息
        String CheckResult = checkInfo();

        //判断返回值是否为空
        if (CheckResult != null){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("错误信息");
            builder.setMessage(CheckResult);
            builder.setPositiveButton("确定",null);
            builder.create().show();
        }else {
            uDB.execSQL("insert into UserInfo values(null,?,?,?,?)",new String[]{
                    name.getText().toString(),
                    pwd.getText().toString(),
                    male,
                    city.getText().toString()});
            this.name.setText("");
            this.pwd.setText("");
            this.city.setText("");
            Toast.makeText(this,"恭喜你注册成功，现在可以登录了！",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            Register.this.finish();
        }
    }

    //当界面退出时销毁
    protected void onDestroy() {
        if (uDB!=null){
            userDB.close();
            uDB.close();
        }
        super.onDestroy();
    }

    //验证账号密码的限制
    private String checkInfo() {
        String c = checked.getText().toString();
        String names = name.getText().toString().trim();
        Cursor cursor = uDB.rawQuery("select * from UserInfo where User_id=?",new String[]{
                names });
        if(cursor.getCount()>0){
            return "用户名已存在！！";
        }else {
            String pwds = pwd.getText().toString().trim();
            if (names == null || "".equals(names))
            {
                return "账号不能为空";
            }

            if (pwds.length()<6 || pwds.length()>16)
            {
                return "密码数字应在6到16位之间";
            }

            if(!c.equals(pwds))
            {
                return "两次密码不一致！";
            }
        }

        return null;
    }

}
