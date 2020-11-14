package com.destinyapp.puskomdik.Activity.menu.Menu1.Finished.Eskul;

import androidx.appcompat.app.AppCompatActivity;

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
import com.destinyapp.puskomdik.Activity.LoginActivity;
import com.destinyapp.puskomdik.Method.Destiny;
import com.destinyapp.puskomdik.Model.ResponseModel;
import com.destinyapp.puskomdik.R;
import com.destinyapp.puskomdik.SharedPreferance.DB_Helper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailEskulActivity extends AppCompatActivity {
    Destiny destiny;
    RelativeLayout Back;
    DB_Helper dbHelper;
    String Username,Password,Nama,Token,Level,Photo;

    //DETAIL Eskul
    String ID_ESKUL,ESKUL,DESKRIPSI,PEMBIMBING,GAMBAR;
    TextView eskul,deskripsi,pembimbing;
    ImageView gambar;
    LinearLayout gabung;
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
    }
    private void ONCLICK(){
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        gabung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
                Call<ResponseModel> Apply = api.ApplyEskul(destiny.AUTH(Token),Token);
                Apply.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        try {
                            if (response.body().getStatusCode().equals("000")){
                                Toast.makeText(DetailEskulActivity.this, "Anda Berhasil Terdaftar Dalam Eskul "+ESKUL, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(DetailEskulActivity.this, EskulActivity.class);
                                startActivity(intent);
                                finish();
                            }else if (response.body().getStatusCode().equals("001") || response.body().getStatusCode().equals("002")){
                                destiny.AutoLogin(Username,Password,DetailEskulActivity.this);
                                Toast.makeText(DetailEskulActivity.this, "Silahkan Coba Lagi", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(DetailEskulActivity.this, "Terjadi Kesalahan ", Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){
                            Toast.makeText(DetailEskulActivity.this, "Terjadi Kesalahan User akan Terlogout", Toast.LENGTH_SHORT).show();
                            dbHelper.Logout();
                            Intent intent = new Intent(DetailEskulActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Toast.makeText(DetailEskulActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    private void Declaration(){
        Back = findViewById(R.id.relativeBack);
        eskul = findViewById(R.id.tvNamaEskul);
        deskripsi = findViewById(R.id.tvDeskripsiEskul);
        pembimbing = findViewById(R.id.tvPembimbing);
        gambar = findViewById(R.id.ivGambar);
        gabung = findViewById(R.id.linearBergabung);
    }
    private void GETDATA(){
        Intent intent = getIntent();
        ID_ESKUL = intent.getExtras().getString("ID_ESKUL");
        ESKUL = intent.getExtras().getString("ESKUL");
        DESKRIPSI = intent.getExtras().getString("DESKRIPSI");
        PEMBIMBING = intent.getExtras().getString("TANGGAL");
        GAMBAR = intent.getExtras().getString("GAMBAR");
        eskul.setText(ESKUL);
        deskripsi.setText(DESKRIPSI);
        pembimbing.setText(PEMBIMBING);
        Glide.with(this)
                .load(GAMBAR)
                .into(gambar);
        Toast.makeText(this, ID_ESKUL, Toast.LENGTH_SHORT).show();
    }
}