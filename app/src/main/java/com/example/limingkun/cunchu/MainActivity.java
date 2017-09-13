package com.example.limingkun.cunchu;

import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
private final static String SharedPreferencesFileName="cinfig";
    private final static String Key_Username="UserName";
    private final static String Key_Xuehao="Xuehao";
    private final static String Key_Xingbie="Xingbie";

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String MyFileName = "fileDemo.txt";
    private EditText ET;
    private EditText ET1;
    private EditText ET2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        preferences=getSharedPreferences(SharedPreferencesFileName,
                MODE_PRIVATE);
        editor=preferences.edit();
        Button btnWrite=(Button)findViewById(R.id.btwrite);
        Button btnRead=(Button)findViewById(R.id.btread);
        ET= (EditText) findViewById(R.id.et);
        ET1= (EditText) findViewById(R.id.et1);
        ET2= (EditText) findViewById(R.id.et2);


        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a= ET.getText().toString();
                String b= ET1.getText().toString();
                String c= ET2.getText().toString();
                String content = a+" "+b+" "+c;
                OutputStream out=null;
                try {
                    FileOutputStream fileOutputStream=openFileOutput(MyFileName,MODE_PRIVATE);
                    out=new BufferedOutputStream(fileOutputStream);

                    try {
                        out.write(content.getBytes(StandardCharsets.UTF_8));
                    }
                    finally {
                        if(out!=null)
                            out.close();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }


            }
        });
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InputStream in=null;
                try {
                    FileInputStream fileInputStream = openFileInput(MyFileName);
                    in=new BufferedInputStream(fileInputStream);

                    int c;
                    StringBuilder stringBuilder=new StringBuilder("");
                    try{
                        while ((c=in.read())!=-1) {
                            stringBuilder.append((char)c);
                        }
                        Toast.makeText(MainActivity.this,stringBuilder.toString(),Toast.LENGTH_LONG).show();
                    }
                    finally {
                        if(in!=null)
                            in.close();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }









               /*String name = preferences.getString(Key_Username, null);
                String xuehao = preferences.getString(Key_Xuehao, null);
                String xingbie=preferences.getString(Key_Xingbie,null);
                if (name != null && xuehao != null)
                    Toast.makeText(MainActivity.this, "用户名:" + name + "学号:" + xuehao+"性别:"+xingbie, Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "无数据", Toast.LENGTH_LONG).show();*/

            }
        });

    }
}
