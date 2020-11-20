package com.destinyapp.puskomdik.Activity.menu.Menu1.Finished.Eskul;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.destinyapp.puskomdik.API.ApiRequest;
import com.destinyapp.puskomdik.API.RetroServer;
import com.destinyapp.puskomdik.Activity.Adapter.AdapterEskul;
import com.destinyapp.puskomdik.Activity.Adapter.TabPagerAdapter;
import com.destinyapp.puskomdik.Activity.LoginActivity;
import com.destinyapp.puskomdik.Method.Destiny;
import com.destinyapp.puskomdik.Model.ResponseModel;
import com.destinyapp.puskomdik.R;
import com.destinyapp.puskomdik.SharedPreferance.DB_Helper;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailEskulActivity extends AppCompatActivity {
    Destiny destiny;
    RelativeLayout Back;
    DB_Helper dbHelper;
    String Username,Password,Nama,Token,Level,Photo;

    //DETAIL Eskul
    String ID_ESKUL;
    TextView eskul,deskripsi,pembimbing;
    ImageView gambar;

    private TabLayout Table;
    private AppBarLayout appBar;
    private ViewPager viewPager;
    private FragmentActivity context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_eskul);
        destiny = new Destiny();
        DBHelper();
        Declaration();
        GETDATA();
        ONCLICK();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    private void DBHelper(){
        dbHelper = new DB_Helper(this);
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Username = cursor.getString(0);
                Password = cursor.getString(1);
                Nama = cursor.getString(2);
                Token = cursor.getString(3);
                Level = cursor.getString(4);
                Photo = cursor.getString(5);
            }
        }
        Table = findViewById(R.id.tableLayout);
        viewPager = findViewById(R.id.viewpager);
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new DetailEskulFragment(),"Detail");
        adapter.AddFragment(new AnggotaEskulFragment(),"Anggota");
        adapter.AddFragment(new GalleryEskulFragment(),"Gallery");
        viewPager.setAdapter(adapter);
        Table.setupWithViewPager(viewPager);
    }
    private void ONCLICK(){
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    private void Declaration(){
        Back = findViewById(R.id.relativeBack);
    }
    private void GETDATA(){
        Intent intent = getIntent();
        ID_ESKUL = intent.getExtras().getString("ID_ESKUL");
    }
}