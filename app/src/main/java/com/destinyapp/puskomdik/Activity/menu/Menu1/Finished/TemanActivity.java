package com.destinyapp.puskomdik.Activity.menu.Menu1.Finished;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.destinyapp.puskomdik.API.ApiRequest;
import com.destinyapp.puskomdik.API.RetroServer;
import com.destinyapp.puskomdik.Activity.Adapter.AdapterSpinner;
import com.destinyapp.puskomdik.Activity.Adapter.AdapterTeman;
import com.destinyapp.puskomdik.Activity.LoginActivity;
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

public class TemanActivity extends AppCompatActivity {
    Destiny destiny;
    RelativeLayout Back;
    DB_Helper dbHelper;
    String Username,Password,Nama,Token,Level,Photo;
    RecyclerView recycler;
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    Spinner spKelas;
    TextView idKelas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teman);
        destiny = new Destiny();
        Back = findViewById(R.id.relativeBack);
        recycler = findViewById(R.id.recycler);
        spKelas = findViewById(R.id.spinnerKelas);
        idKelas = findViewById(R.id.tvId);
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
        GetKelas();
        spKelas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long ld) {
                try {
                    DataModel clickedItem = (DataModel) adapterView.getItemAtPosition(position);
                    String clickedItems = clickedItem.getId_kelas();
                    idKelas.setText(clickedItems);
                    Logic();
                }catch (Exception e){
                    Log.i("Error : ",e.toString());
                    Toast.makeText(TemanActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                destiny.Back(TemanActivity.this);
            }
        });
    }
    private void GetKelas(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> Data=api.KelasAll(destiny.AUTH(Token));
        Data.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if (response.body().getStatusCode().equals("000")){
                        mItems=response.body().getData();
                        AdapterSpinner adapter = new AdapterSpinner(TemanActivity.this,mItems);
                        spKelas.setAdapter(adapter);
                    }else if (response.body().getStatusCode().equals("001") || response.body().getStatusCode().equals("002")){
                        destiny.AutoLogin(Username,Password,TemanActivity.this);
                        Intent intent = new Intent(TemanActivity.this,TemanActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(TemanActivity.this, "Terjadi Kesalahan ", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(TemanActivity.this, "Terjadi Kesalahan User akan Terlogout", Toast.LENGTH_SHORT).show();
                    dbHelper.Logout();
                    Intent intent = new Intent(TemanActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(TemanActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Logic(){
        mManager = new GridLayoutManager(TemanActivity.this,2);
        recycler.setLayoutManager(mManager);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> Temans = api.Teman(destiny.AUTH(Token),idKelas.getText().toString());
        Temans.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if (response.body().getStatusCode().equals("000")){
                        mItems=response.body().getData();
                        mAdapter = new AdapterTeman(TemanActivity.this,mItems);
                        recycler.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }else if (response.body().getStatusCode().equals("001") || response.body().getStatusCode().equals("002")){
                        destiny.AutoLogin(Username,Password,TemanActivity.this);
                        Intent intent = new Intent(TemanActivity.this,TemanActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(TemanActivity.this, "Terjadi Kesalahan ", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(TemanActivity.this, "Terjadi Kesalahan User akan Terlogout", Toast.LENGTH_SHORT).show();
                    dbHelper.Logout();
                    Intent intent = new Intent(TemanActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(TemanActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        destiny.Back(TemanActivity.this);
    }
}