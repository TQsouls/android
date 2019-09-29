package com.example.myapplication2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.util.Random;

public class IOtest extends AppCompatActivity {

    //初始化两个控件
    private EditText w ;
    private EditText r ;

    //文件名
    private String fileName ="context.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iotest);
    }

    //将数据写入文件
    public void write(View view){
        w = findViewById(R.id.write);
        try {
            FileOutputStream fos = openFileOutput(fileName, Context.MODE_APPEND);
            fos.write(w.getText().toString().getBytes());
            w.setText("");
            AlertDialog.Builder st = new AlertDialog.Builder(this);
            st.setTitle("成功提示");
            st.setMessage("写入文件成功！！");
            st.setPositiveButton("确定",null);
            st.create().show();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //从文件中读取数据
    public void read(View view){
        r = findViewById(R.id.read);
        try {
            FileInputStream fls = openFileInput(fileName);
            StringBuffer stringBuffer = new StringBuffer("");
            byte[] bytes = new byte[256];
            int hasRead = 0;
            while((hasRead=fls.read(bytes))!=-1){
                stringBuffer.append(new String(bytes,0,hasRead));
            }
            r.setText(stringBuffer.toString());
            fls.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //将数据写入SD卡中的文件
    public void sdWrite(View view){
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            if ((ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)){
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
            }else {
                sdInput();
            }
        }
    }

    //SDInput的调用
    public void sdInput(){
        w = findViewById(R.id.write);
        try {
            File sdDir = new Environment().getExternalStorageDirectory();
            File des = new File((sdDir.getCanonicalPath()+File.separator+fileName));

            RandomAccessFile raf = new RandomAccessFile(des,"rw");
            raf.seek(des.length());
            raf.write(w.getText().toString().getBytes());
            w.setText("");
            raf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //从SD卡中读取数据
    public void sdRead(View view){
        if(Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)){
            if(ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE},2);
            }else {
                sdOutput();
            }
        }
    }

    //掉用sdOutput
    public void sdOutput(){
        r = findViewById(R.id.read);
        StringBuffer sBuffer = new StringBuffer("");
        try {
            File sdCard = Environment.getExternalStorageDirectory();
            File des = new File(sdCard.getCanonicalPath()+File.separator+fileName);

            FileInputStream fls = new FileInputStream(des);
            byte[] buffer = new byte[64];
            int hasReand = 0;

            while ((hasReand = fls.read(buffer))!=-1){
                sBuffer.append(new String(buffer,0,hasReand));
            }
            fls.close();
            r.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取授权结果
    public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResult){
        if (requestCode==1){
            if (grantResult.length> 0 && grantResult[0]==PackageManager.PERMISSION_GRANTED){
                sdInput();
            }else {
                Toast.makeText(this,"您拒绝了相关的请求，写入数据失败！",Toast.LENGTH_SHORT).show();
            }
            }else if (requestCode==2){
            if (grantResult.length> 0 && grantResult[0]==PackageManager.PERMISSION_GRANTED){
                sdOutput();
            }else {
                Toast.makeText(this,"您拒绝了相关的请求，写入数据失败！",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
