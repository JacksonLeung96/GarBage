package com.example.administrator.providertest;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private  String newId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addData = findViewById(R.id.add_data);
        Button queryData = findViewById(R.id.query_data);
        Button deleteData = findViewById(R.id.delete_data);
        Button updataData = findViewById(R.id.update_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.administrator.databasetest.provider/contacts");
                ContentValues values = new ContentValues();
                values.put("name","梁华岳");
                values.put("phoneNumber","13421483564");
                values.put("sex","男");
                Uri newUri = getContentResolver().insert(uri,values);
                newId = newUri.getPathSegments().get(1);
                Log.d("MainActivity","添加数据到数据库"+newUri+" ID: "+newId);
            }
        });
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.administrator.databasetest.provider/contacts");
                Cursor cursor = getContentResolver().query(uri,null,null,null,null);
                if(cursor!=null){
                    while (cursor.moveToNext()){
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String phoneNumber = cursor.getString(cursor.getColumnIndex("phoneNumber"));
                        String sex = cursor.getString(cursor.getColumnIndex("sex"));
                        Log.d("MainActivity","联系人姓名是: " + name);
                        Log.d("MainActivity","联系人电话是: " + phoneNumber);
                        Log.d("MainActivity","联系人性别是: " + sex);
                        Log.d("MainActivity","uri:"+uri);
                        Toast.makeText(MainActivity.this,"contacts name is : "+name,Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this,"contacts phoneNumber is : "+phoneNumber,Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this,"contacts sex is : "+sex,Toast.LENGTH_SHORT).show();
                    }
                    cursor.close();
                }
            }
        });
        updataData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.administrator.databasetest.provider/contacts/"+newId);
                ContentValues values = new ContentValues();
                values.put("name","梁华岳");
                values.put("phoneNumber","13421483564");
                values.put("sex","女");
                getContentResolver().update(uri,values,null,null);
                Log.d("MainActivity","更新数据库，性别为女"+uri);
            }
        });
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.administrator.databasetest.provider/contacts/"+newId);

                getContentResolver().delete(uri,null,null);
                Log.d("MainActivity","删除表contacts中ID=1的记录："+uri+ "ID:" +newId);
            }
        });
    }
}
