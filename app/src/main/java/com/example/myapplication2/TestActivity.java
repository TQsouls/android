package com.example.myapplication2;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class TestActivity extends AppCompatActivity {

    private Button btn1,btn2;       //声明控件按钮

    private Spinner schoolSpinner;
    private String[] schools={"香蕉","苹果","水蜜桃","菠萝"};


    //菜单事件加载
    @Override
    public boolean onCreateOptionsMenu(Menu menu){                                                  //声明menu变量
        getMenuInflater().inflate(R.menu.option_menu,menu);                                         //导入menu的XMl
        return super.onCreateOptionsMenu(menu);                                                     //自动选择menu
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        registerForContextMenu(btn1);
        registerForContextMenu(btn2);

        schoolSpinner=findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this,android.R.layout.simple_list_item_1,schools);
        schoolSpinner.setAdapter(adapter);
    }


    //两个按钮的长按事件  然后显示菜单
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuInfo){
        switch(v.getId()){
            case R.id.btn1:
                getMenuInflater().inflate(R.menu.context_btn1,menu);
                break;
            case R.id.btn2:
                getMenuInflater().inflate(R.menu.context_btn2,menu);
                break;
            default:break;
        }
        super.onCreateContextMenu(menu,v,menuInfo);
    }

    public boolean onContextItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.red:
                btn1.setTextColor(Color.RED);
                break;
            case R.id.green:
                btn1.setTextColor(Color.GREEN);
                break;
            case R.id.blue:
                btn1.setTextColor(Color.BLUE);
                break;
            case R.id.rename:
                AlertDialog.Builder nameBuilder = new AlertDialog.Builder(this);
                nameBuilder.setTitle("请输入新的名字");
                final EditText nameText = new EditText(this);
                nameBuilder.setView(nameText);
                nameBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        btn1.setText(nameText.getText());
                    }
                });
                nameBuilder.setNegativeButton("取消",null);
                nameBuilder.create();
                nameBuilder.show();
                break;
            case R.id.bg_red:
                btn2.setBackgroundColor(Color.RED);
                break;
            case R.id.bg_green:
                btn2.setBackgroundColor(Color.GREEN);
                break;
            case R.id.bg_blue:
                btn2.setBackgroundColor(Color.BLUE);
                break;

        }
        return super.onContextItemSelected(item);
    }

    //警告弹窗
    public void play(View v)
    {
        AlertDialog.Builder exitBuilder = new AlertDialog.Builder(this);  //声明Dialog对象
        exitBuilder.setTitle("退出提示");                                                            //设置Dialog标题
        exitBuilder.setMessage("你确定要退出吗？");                                                   //设置提示消息
        exitBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {           //声明事件操作
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {                           //设置按钮单击事件
                TestActivity.this.finish();                                                         //结束这个界面
            }
        });
        exitBuilder.setPositiveButton("取消",null);                                     //添加取消按钮
        exitBuilder.create().show();                                                                //执行AlertDialog
    }

    //选择弹窗
    public void Select(View view)
    {
        String[] items=new String[]{"在线","隐身","忙碌","离线","其他"};                               //初始化数组
        AlertDialog.Builder singleBuilder=new AlertDialog.Builder(this);                    //初始化AlertDialog
        singleBuilder.setIcon(R.mipmap.ic_launcher);                                               //设置图标
        singleBuilder.setTitle("请选择在线状态");                                                   //设置标题
        singleBuilder.setSingleChoiceItems(items,1,null);                     //设置多选item
        singleBuilder.setPositiveButton("确定",null);                                //添加按钮
        singleBuilder.setNegativeButton("取消",null);
        singleBuilder.create().show();
    }

    //自由弹窗
    public void Open(View view)
    {
        AlertDialog.Builder loginBuilder = new AlertDialog.Builder(this);
        View loginView = getLayoutInflater().inflate(R.layout.dialog_login,null);
        loginBuilder.setView(loginView);
        loginBuilder.setPositiveButton("确定",null);
        loginBuilder.setNegativeButton("取消",null);
        loginBuilder.create().show();
    }

    //发送短信成功！！！！
    public void send(View view)
    {
        EditText num = findViewById(R.id.number); String n = num.getText().toString().trim();
        EditText text = findViewById(R.id.tx); String t = text.getText().toString().trim();
        if (n.equals("") || t.equals("")){
            return;
        }else{
            Uri uri = Uri.parse("smsto:"+n);
            Intent intent = new Intent(Intent.ACTION_SENDTO,uri);
            intent.putExtra("sms_body",t);
            startActivity(intent);
        }

    }

    //浏览网页调用成功！！！！
    public void safeInter(View view){
        EditText go = findViewById(R.id.editTextWeb); String Web = go.getText().toString().trim();
        if (Web.equals("")){
            return;
        }else {
            Uri uri = Uri.parse("http://"+Web);
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);
        }
    }

}
