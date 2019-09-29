package com.example.myapplication2;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TestText extends AppCompatActivity {
    //声明按钮成员变量
    private Button red,blue,yellow,big,small;

    //声明文本框
    private TextView testText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);

        //找到控件id
        testText=(TextView) findViewById(R.id.testText);
        red=(Button) findViewById(R.id.red);
        blue=(Button) findViewById(R.id.blue);
        yellow=(Button) findViewById(R.id.yellow);

        big=(Button )findViewById(R.id.big);
        small=(Button )findViewById(R.id.small);

        //创建新的颜色监听对象
        ColorListener myColorListenr = new ColorListener();

        //注册监听器
        red.setOnClickListener(myColorListenr);
        yellow.setOnClickListener(myColorListenr);
        blue.setOnClickListener(myColorListenr);

        //创建新的文本大小事件
        SizeListener mysizeListener = new SizeListener(testText);
        big.setOnClickListener(mysizeListener);
        small.setOnClickListener(mysizeListener);


    }

    //设置颜色的class
    private class ColorListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.red:
                    testText.setTextColor(Color.RED);break;
                case R.id.blue:
                    testText.setTextColor(Color.BLUE);break;
                case R.id.yellow:
                    testText.setTextColor(Color.YELLOW);break;
                default:break;
            }
        }
    }

    //设置字体的class
    public class SizeListener implements View.OnClickListener{

        private TextView tv;
        public SizeListener(TextView textView){
            this.tv = textView;
        }

        @Override
        public void onClick(View view) {
            float f = tv.getTextSize();
            switch (view.getId()){
                case R.id.big:
                    f=f+2;
                    break;
                case R.id.small:
                    f=f-2;
                    break;
                default:break;
            }
            if (f<=8){
                f=8;
            }
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,f);
        }
    }
}
