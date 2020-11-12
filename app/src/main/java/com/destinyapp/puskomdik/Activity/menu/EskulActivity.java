package com.destinyapp.puskomdik.Activity.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.destinyapp.puskomdik.Method.Destiny;
import com.destinyapp.puskomdik.R;

public class EskulActivity extends AppCompatActivity {
    Destiny destiny;
    RelativeLayout Back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eskul);
        destiny = new Destiny();
        Back = findViewById(R.id.relativeBack);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                destiny.Back(EskulActivity.this);
            }
        });
    }
    @Override
    public void onBackPressed() {
        destiny.Back(EskulActivity.this);
    }
}