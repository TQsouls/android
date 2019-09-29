package com.example.myapplication2;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.myapplication2.User.LoginActivity;

import java.util.Random;

public class ScendActivity extends AppCompatActivity {

    private TextView userInfo;

    //加载Menu布局
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //为菜单添加事件处理
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.invalidate:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                ScendActivity.this.finish();
                break;
            case R.id.exit:
                this.finish();
                break;

            default:break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scend);

        userInfo = findViewById(R.id.textView);
        Intent intent = getIntent();
        if ((intent!=null))
        {
            userInfo.setText(intent.getStringExtra("userName"));
        }
    }

    //拨打电话函数
    public void Call(View view)
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},1);
        }else {
            dail();
        }
    }

    //打电话的调用
    public void dail(){
        EditText call = findViewById(R.id.txCall);
        String number = call.getText().toString();
        if(number.equals(""))
        {
            return;
        }
        else
        {
            Uri uri = Uri.parse("tel:"+number);
            Intent intent = new Intent(Intent.ACTION_DIAL,uri);
            startActivity(intent);
        }
    }

    //权限请求
    public void onRequestPermissionsResult(int requestCode, String[]
                                           permissions,int[] grantResults){
        if (requestCode==1) {
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                dail();
            }else {
                Toast.makeText(this, "您拒绝了相关授权，无法进行拨号操作！", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //支付函数
    public void goPay(View view)
    {
        DialogActivity lag = new DialogActivity();
        lag.show( getSupportFragmentManager(),"DialogActivity");
    }

    //文本测试
    public void next(View view){
        TextView textView =findViewById(R.id.textView);
        textView.setText("干嘛点我！");

    }

    //跳转到文本测试界面
    public void Test(View v)
    {
        Intent intent= new Intent();
        intent.setClass(ScendActivity.this,TestActivity.class);
        startActivity(intent);
    }
    @SuppressLint("SetTextI18n")

    //本页猜数字游戏
    public void Guss(View view)
    {

        Random random=new Random();         //生成随机数
        String str=String.valueOf(random.nextInt(10));
        TextView textView =findViewById(R.id.textView3);   //生成view控件
        TextView textView1 =findViewById(R.id.textView4);
        TextView textView2=findViewById(R.id.textView5);

        EditText editText=findViewById(R.id.tx2);

        String a=editText.getText().toString().trim();      //强制转换
        if (a.equals(""))
        {

            textView2.setVisibility(View.VISIBLE);
        }
        else
            {
                textView2.setVisibility(View.INVISIBLE);
            if (str.equals(a))
            {
                textView.setText("猜对了，该数字是"+str);
                textView.setVisibility(View.VISIBLE);
                textView1.setVisibility(View.INVISIBLE);
            }
            else
            {
                textView1.setText("猜错了，该数字是"+str);
                textView.setVisibility(View.INVISIBLE);
                textView1.setVisibility(View.VISIBLE);
            }
        }
    }
    //文本的的测试按钮
    public void testText(View view){
        Intent intent = new Intent();
        intent.setClass(this,TestText.class);
        startActivity(intent);
    }

    //游戏界面跳转
    public void Game(View v){
        Intent intent = new Intent();
        intent.setClass(this,GameActivity.class);
        intent.putExtra("userName",userInfo.getText().toString());
        startActivity(intent);
    }

    //图片吧轮播界面
    public void testImage(View view){
        Intent intent = new Intent();
        intent.setClass(this,ImagesActivity.class);
        startActivity(intent);
    }

    //登录测试
    public void login(View view){
        Intent intent = new Intent(this, FragmentActivity.class);
        startActivity(intent);
    }

    //输入输出流测试
    public void IOTest(View view){
        Intent intent = new Intent(this,IOtest.class);
        startActivity(intent);
    }

    //备忘录的调用
    public void Date(View view){
        Intent intent = new Intent(this,Date.class);
        startActivity(intent);
    }

}
