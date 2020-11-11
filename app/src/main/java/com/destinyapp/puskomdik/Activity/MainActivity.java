package com.destinyapp.puskomdik.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;

import com.destinyapp.puskomdik.R;
import com.destinyapp.puskomdik.SharedPreferance.DB_Helper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Handler handler = new Handler();
        final DB_Helper dbHelper = new DB_Helper(MainActivity.this);
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }else{
            handler.postDelayed(new Runnable() {
                public void run() {
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 3000); //3000 L = 3 detik
        }
    }
}