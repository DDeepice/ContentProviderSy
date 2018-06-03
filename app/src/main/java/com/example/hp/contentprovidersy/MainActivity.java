package com.example.hp.contentprovidersy;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.net.MalformedURLException;

public class MainActivity extends AppCompatActivity {

    private MyDataBaseHelper dbHelper;

    private Button createBt;
    private Button insertBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MyDataBaseHelper(this,"Content.db",null,1);

        createBt = findViewById(R.id.create);
        insertBt = findViewById(R.id.insert);

        createBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();

                Toast.makeText(MainActivity.this, "数据库创建成功", Toast.LENGTH_SHORT).show();
            }
        });

        insertBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();

                //开始组装第一组数据

                values.put("name","Wang LanHua");
                values.put("number","13420203561");
                values.put("gender","male");

                db.insert("Contacts",null,values);

                values.clear();

                values.put("name","Fat DaHai");
                values.put("number","13435787340");
                values.put("gender","female");
                db.insert("Contacts",null,values);

                Toast.makeText(MainActivity.this, "数据添加成功", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
