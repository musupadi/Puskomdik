package com.destinyapp.puskomdik.Activity.menu.Menu1.Finished;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.destinyapp.puskomdik.Method.Destiny;
import com.destinyapp.puskomdik.R;

public class ProfilSekolahActivity extends AppCompatActivity {
    Destiny destiny;
    RelativeLayout Back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_sekolah);
        destiny = new Destiny();
        Back = findViewById(R.id.relativeBack);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                destiny.Back(ProfilSekolahActivity.this);
            }
        });
    }

    @Override
    public void onBackPressed() {
        destiny.Back(ProfilSekolahActivity.this);
    }
}