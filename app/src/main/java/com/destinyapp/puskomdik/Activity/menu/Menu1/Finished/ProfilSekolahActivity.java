package com.destinyapp.puskomdik.Activity.menu.Menu1.Finished;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.destinyapp.puskomdik.API.ApiRequest;
import com.destinyapp.puskomdik.API.RetroServer;
import com.destinyapp.puskomdik.Activity.LoginActivity;
import com.destinyapp.puskomdik.Activity.MainActivity;
import com.destinyapp.puskomdik.Method.Destiny;
import com.destinyapp.puskomdik.Model.DataModel;
import com.destinyapp.puskomdik.Model.ResponseModel;
import com.destinyapp.puskomdik.R;
import com.destinyapp.puskomdik.SharedPreferance.DB_Helper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilSekolahActivity extends AppCompatActivity {
    Destiny destiny;
    RelativeLayout Back;
    ImageView Logo;
    TextView NamaSekolah,Alamat,NoTelp,NoRekening,NamaBank,PemegangRekening,Yayasan,AlamatYayasan,NPSN,TahunBeroprasi,StatuSekolah,SK,TahunDidirikan,StatusTanah,LuasTanah;
    DB_Helper dbHelper;
    String Username,Password,Nama,Token,Level,Photo;
    RecyclerView recycler,recyclerKabar;
    private List<DataModel> mItems = new ArrayList<>();
    private List<DataModel> mItems2 = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_sekolah);
        destiny = new Destiny();
        Logo = findViewById(R.id.ivLogo);
        NamaSekolah = findViewById(R.id.tvNamaProfileSekolah);
        Alamat = findViewById(R.id.tvAlamatSekolah);
        NoTelp = findViewById(R.id.tvTelponSekolah);
        NoRekening = findViewById(R.id.tvRekeningSekolah);
        NamaBank = findViewById(R.id.tvNamaBankSekolah);
        PemegangRekening = findViewById(R.id.tvNamaPemegangRekeningSekolah);
        Yayasan = findViewById(R.id.tvNamaYayasan);
        AlamatYayasan = findViewById(R.id.tvAlanmatYayasan);
        NPSN = findViewById(R.id.tvNPSN);
        TahunBeroprasi = findViewById(R.id.tvTahunBeroperasiSekolah);
        StatuSekolah = findViewById(R.id.tvStatusSekolah);
        SK = findViewById(R.id.tvSkKelambanganSekolah);
        TahunDidirikan = findViewById(R.id.tvTahunDidirikanSekolah);
        StatusTanah = findViewById(R.id.tvStatsTanahSekolah);
        LuasTanah = findViewById(R.id.tvLuasTanahSekolah);
        Back = findViewById(R.id.relativeBack);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                destiny.Back(ProfilSekolahActivity.this);
            }
        });
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
        GetSekolah();
    }
    private void GetSekolah(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> Point = api.ProfileSekolah(destiny.AUTH(Token));
        Point.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if (response.body().getStatusCode().equals("000")){
                        NamaSekolah.setText(response.body().getData().get(0).getNama_sekolah());
                        Alamat.setText(response.body().getData().get(0).getNama_sekolah());
                        NPSN.setText(response.body().getData().get(0).getNpsn());
                        Glide.with(ProfilSekolahActivity.this)
                                .load(destiny.BASE_URL()+response.body().getData().get(0).getLogo_sekolah())
                                .into(Logo);
                    }else if (response.body().getStatusCode().equals("001") || response.body().getStatusCode().equals("002")){
                        destiny.AutoLogin(Username,Password,ProfilSekolahActivity.this);
                        Intent intent = new Intent(ProfilSekolahActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(ProfilSekolahActivity.this, "Terjadi Kesalahan ", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Log.i("Error : ",e.toString());
                    Toast.makeText(ProfilSekolahActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    dbHelper.Logout();
                    Intent intent = new Intent(ProfilSekolahActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(ProfilSekolahActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        destiny.Back(ProfilSekolahActivity.this);
    }
}