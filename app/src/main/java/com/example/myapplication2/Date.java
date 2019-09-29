package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.myapplication2.beans.MyDatabaseHelper;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Date extends AppCompatActivity {

    //初始化控件昵称
    private EditText date,subject, body;

    //初始化列表
    private ListView result;

    //初始化标题
    private LinearLayout title;

    //初始化SQLite
    private MyDatabaseHelper mHelper;
    private SQLiteDatabase mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //指定数据库名字
        mHelper = new MyDatabaseHelper(this, "memento.db", null, 1);

        //打开数据库
        mDB = mHelper.getReadableDatabase();

        setContentView(R.layout.activity_date);

        //找到控件的id
        date = findViewById(R.id.date);
        subject = findViewById(R.id.subject);
        body = findViewById(R.id.body);
        result = findViewById(R.id.result);
        title = (LinearLayout) findViewById(R.id.title);
        title.setVisibility(View.INVISIBLE);
    }

    //页面销毁是调用
    protected void onDestroy() {
        if (mDB != null) {
            mDB.close();
            mHelper.close();
        }
        super.onDestroy();
    }

    //选择日期
    public void chooseDate(View view) {
        date = findViewById(R.id.date);
        Calendar c = Calendar.getInstance();
        new DatePickerDialog(Date.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                date.setText(year + "-" + (month + 1) + "-" + day);
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
    }

    //添加备忘录函数
    public void add(View view) {
        mDB.execSQL("insert into memento_tb values(null,?,?,?)", new String[]{
                subject.getText().toString(),
                body.getText().toString(),
                date.getText().toString()});
        this.subject.setText("");
        this.body.setText("");
        this.date.setText("");
        title.setVisibility(View.INVISIBLE);
        Toast.makeText(Date.this, "添加备忘录成功！", Toast.LENGTH_SHORT).show();
        result.setAdapter(null);
    }

    //查询备忘录录的内容
    public void query(View view) {
        title.setVisibility(View.VISIBLE);
        Cursor cursor = mDB.rawQuery("select *from memento_tb where subject like ? and body like ? and date like ?",
                new String[]{
                "%" + subject.getText().toString() + "%",
                "%" + body.getText().toString() + "%",
                "%" + date.getText().toString() + "%"});

        SimpleCursorAdapter resultAdapter = new SimpleCursorAdapter(this, R.layout.itme, cursor, new String[]{
                "_id", "subject", "body", "date"}, new int[]{
                R.id.memento_num,
                R.id.memento_subject,
                R.id.memento_body,
                R.id.memento_date},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        result.setAdapter(resultAdapter);
    }
}
