package com.example.myapplication2;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication2.DB.GameResultDB;
import com.example.myapplication2.beans.MyDatabaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    //声明color按钮
    private Button col1,col2,col3,col4,col5,col6,col7,col8,col9,col10,col11,col12,col13,col14,col15,col16,start,renew;

    //声明全局变量
     private String num,num1,num2,num3,num4,num5,num6,num8,num9,num10,num11,num12,num13,num14,num15,num16;

    //声明文本控件
    public TextView aim,score,times;

    private TextView user;
    private ListView result;

    //初始化时间
    private CountDownTimer timer;

    //初始化数据库
    private GameResultDB gDB;
    private SQLiteDatabase mDB;

    private LinearLayout title;

    //初始化分数
    int grand=0;
    String s;
    String str[] = new  String[15];

    Random random=new Random(System.currentTimeMillis());

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.colorgame);
        user=findViewById(R.id.User);
        result=findViewById(R.id.Top);
        Intent intent = getIntent();
        if (intent!=null){
            user.setText(intent.getStringExtra("userName"));
        }

        title = findViewById(R.id.title);
        title.setVisibility(View.INVISIBLE);

        //指定数据库名字
        gDB = new GameResultDB(this, "Resulted.db", null, 1);
        //打开数据库
        mDB = gDB.getReadableDatabase();


        //找到控件id
        col1=findViewById(R.id.col1);
        col2=findViewById(R.id.col2);
        col3=findViewById(R.id.col3);
        col4=findViewById(R.id.col4);
        col5=findViewById(R.id.col5);
        col6=findViewById(R.id.col6);
        col7=findViewById(R.id.col7);
        col8=findViewById(R.id.col8);
        col9=findViewById(R.id.col9);
        col10=findViewById(R.id.col10);
        col11=findViewById(R.id.col11);
        col12=findViewById(R.id.col12);
        col13=findViewById(R.id.col13);
        col14=findViewById(R.id.col14);
        col15=findViewById(R.id.col15);
        col16=findViewById(R.id.col16);

        aim=findViewById(R.id.aim);

        score=findViewById(R.id.score);
        start=findViewById(R.id.start);
        renew=findViewById(R.id.renew);



        //设置注册监听器
        ColorListener colorLis = new ColorListener();

        //添加监听事件
        col1.setOnClickListener(colorLis);
        col2.setOnClickListener(colorLis);
        col3.setOnClickListener(colorLis);
        col4.setOnClickListener(colorLis);
        col5.setOnClickListener(colorLis);
        col6.setOnClickListener(colorLis);
        col7.setOnClickListener(colorLis);
        col8.setOnClickListener(colorLis);
        col9.setOnClickListener(colorLis);

        col10.setOnClickListener(colorLis);
        col11.setOnClickListener(colorLis);
        col12.setOnClickListener(colorLis);
        col13.setOnClickListener(colorLis);
        col14.setOnClickListener(colorLis);
        col15.setOnClickListener(colorLis);
        col16.setOnClickListener(colorLis);

        start.setOnClickListener(colorLis);
        renew.setOnClickListener(colorLis);

        //这是游戏结束
    }


    int rand[] = new int[16];  // 用于存放所取的值的数组

    private int[] creatRandom() {
        int number = 16;// 控制随机数产生的范围
        List arr = new ArrayList();
        for (int i = 0; i < 16; i++)
            arr.add(i + 1);// 为ArrayList添加元素
        for (int j = 0; j < rand.length; j++)
        {
            int index = (int) (Math.random() * number);// 产生一个随机数作为索引
            rand[j] = (int) arr.get(index);
            arr.remove(index);// 移除已经取过的元素
            number--;// 将随机数范围缩小1
        }
        return rand;
    }


    //事件的开始
    public class ColorListener implements View.OnClickListener{

        //开始函数
        @Override
        public void onClick(View view) {

            switch (view.getId()){

                case R.id.start:
                    setTimer();
                    rand();
                    break;

                case R.id.renew:
                    if(timer!=null)
                    {
                        timer.cancel();
                        times.setText("0");
                        setTimer();
                        rand();
                    }
                  else {
                        times.setText("0");
                        setTimer();
                        rand();
                    }
                    break;

                case R.id.col1:
                    timer.cancel();
                    if (s.equals(num)){
                        setTimer();
                        grand++;
                        String num = String.valueOf(grand);
                        score.setText(num);
                    }else {
                        gameOver();
                    }
                    rand();
                    break;

                case R.id.col2:
                    timer.cancel();
                    if (s.equals(num1)){
                        setTimer();
                        grand++;
                        String num = String.valueOf(grand);
                        score.setText(num);
                    }else {
                        gameOver();
                    }
                    rand();
                    break;

                case R.id.col3:
                    timer.cancel();
                    if (s.equals(num2)){
                        setTimer();
                        grand++;
                        String num = String.valueOf(grand);
                        score.setText(num);
                    }else {
                        gameOver();

                    }
                    rand();
                    break;

                case R.id.col4:
                    timer.cancel();
                    if (s.equals(num3)){
                        setTimer();
                        grand++;
                        String num = String.valueOf(grand);
                        score.setText(num);
                    }else {
                        gameOver();
                    }
                    rand();
                    break;

                case R.id.col5:
                    timer.cancel();
                    if (s.equals(num4)){
                        setTimer();
                        grand++;
                        String num = String.valueOf(grand);
                        score.setText(num);
                    }else {
                        gameOver();
                    }
                    rand();
                    break;

                case R.id.col6:
                    timer.cancel();
                    setTimer();
                    if (s.equals(num5)){
                        grand++;
                        String num = String.valueOf(grand);
                        score.setText(num);
                    }else {
                        gameOver();
                    }
                    rand();
                    break;

                case R.id.col7:
                    timer.cancel();
                    if (s.equals(num6)){
                        setTimer();
                        grand++;
                        String num = String.valueOf(grand);
                        score.setText(num);
                    }else {
                        gameOver();
                    }
                    rand();
                    break;

                case R.id.col8:
                    timer.cancel();
                    if (s.equals(num8)){
                        setTimer();
                        grand++;
                        String num = String.valueOf(grand);
                        score.setText(num);
                    }else {
                        gameOver();
                    }
                    rand();
                    break;

                case R.id.col9:
                    timer.cancel();
                    if (s.equals(num9)){
                        setTimer();
                        grand++;
                        String num = String.valueOf(grand);
                        score.setText(num);
                    }else {
                        gameOver();
                    }
                    rand();
                    break;

                case R.id.col10:
                    timer.cancel();
                    if (s.equals(num10)){
                        setTimer();
                        grand++;
                        String num = String.valueOf(grand);
                        score.setText(num);
                    }else {
                        gameOver();
                    }
                    rand();
                    break;

                case R.id.col11:
                    timer.cancel();
                    if (s.equals(num11)){
                        setTimer();
                        grand++;
                        String num = String.valueOf(grand);
                        score.setText(num);
                    }else {
                        gameOver();
                    }
                    rand();
                    break;

                case R.id.col12:
                    timer.cancel();
                    if (s.equals(num12)){
                        setTimer();
                        grand++;
                        String num = String.valueOf(grand);
                        score.setText(num);
                    }else {
                        gameOver();
                    }
                    rand();
                    break;

                case R.id.col13:
                    timer.cancel();
                    if (s.equals(num13)){
                        setTimer();
                        grand++;
                        String num = String.valueOf(grand);
                        score.setText(num);
                    }else {
                        gameOver();
                    }
                    rand();
                    break;

                case R.id.col14:
                    timer.cancel();
                    if (s.equals(num14)){
                        setTimer();
                        grand++;
                        String num = String.valueOf(grand);
                        score.setText(num);
                    }else {
                        gameOver();
                    }
                    rand();
                    break;

                case R.id.col15:
                    timer.cancel();
                    if (s.equals(num15)){
                        setTimer();
                        grand++;
                        String num = String.valueOf(grand);
                        score.setText(num);
                    }else {
                        gameOver();
                    }
                    rand();
                    break;

                case R.id.col16:
                    timer.cancel();
                    if (s.equals(num16)){
                        setTimer();
                        grand++;
                        String num = String.valueOf(grand);
                        score.setText(num);
                    }else {
                        gameOver();
                    }
                    rand();
                    break;
            }
        }

        //封装随机函数
        private void rand() {

            creatRandom();
             num = String.valueOf(rand[0]);
             num1 = String.valueOf(rand[1]);
             num2 = String.valueOf(rand[2]);
             num3 = String.valueOf(rand[3]);
             num4 = String.valueOf(rand[4]);
             num5 = String.valueOf(rand[5]);
             num6 = String.valueOf(rand[6]);
             num8 = String.valueOf(rand[7]);
             num9 = String.valueOf(rand[8]);
             num10 = String.valueOf(rand[9]);
             num11 = String.valueOf(rand[10]);
             num12 = String.valueOf(rand[11]);
             num13 = String.valueOf(rand[12]);
             num14 = String.valueOf(rand[13]);
             num15 = String.valueOf(rand[14]);
             num16 = String.valueOf(rand[15]);

            s = String.valueOf(random.nextInt(17));

            aim.setText(s);

            col1.setText(num);
            col2.setText(num1);
            col3.setText(num2);
            col4.setText(num3);
            col5.setText(num4);
            col6.setText(num5);
            col7.setText(num6);
            col8.setText(num8);
            col9.setText(num9);
            col10.setText(num10);
            col11.setText(num11);
            col12.setText(num12);
            col13.setText(num13);
            col14.setText(num14);
            col15.setText(num15);
            col16.setText(num16);

        }

    }

    //游戏结束对话框
    private void gameOver(){

        AlertDialog.Builder exitBuilder = new AlertDialog.Builder(this);                     //声明Dialog对象
        exitBuilder.setTitle("游戏结束");                                                            //设置Dialog标题
        exitBuilder.setMessage("您的分数是："+grand);                                                   //设置提示消息

        exitBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {           //声明事件操作
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //强制转化分数Tostring
                String a = String.valueOf(grand);
                //将分数写入数据库
                mDB.execSQL("insert into result values(null,?,?)",new String[]{
                        user.getText().toString(),
                        a,
                });
                grand=0;
                String num = String.valueOf(grand);
                score.setText(num);
                aim.setText("");
                col1.setText("");  col2.setText("");   col3.setText("");col4.setText("");
                col5.setText("");  col6.setText("");   col7.setText("");col8.setText("");
                col9.setText("");  col10.setText("");  col11.setText("");col12.setText("");
                col13.setText(""); col14.setText("");  col15.setText("");col16.setText("");

            }
        });
        exitBuilder.create().show();                                                                //执行AlertDialog
    }

    //查询排行榜
    public void query(View view) {

        title.setVisibility(View.VISIBLE);

        Cursor cursor = mDB.rawQuery("select * from result order by cast(score as '-9999')", null);

        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.gameresult, cursor, new String[]{
                "_id", "User_id", "score"}, new int[]{
                R.id.number,
                R.id.userInfo,
                R.id.result}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        result.setAdapter(simpleCursorAdapter);
    }

    protected void onDestroy() {
        if (mDB != null) {
            mDB.close();
            gDB.close();
        }
        super.onDestroy();
    }
    //时间倒计时
    private void setTimer(){
        timer= new CountDownTimer(3*1000,1000) {
        @Override
        public void onTick(long l) {
          times=findViewById(R.id.times);
          times.setText(""+l/1000+"");
        }

        @Override
        public void onFinish() {
            gameOver();
        }
    }.start();

}

}
