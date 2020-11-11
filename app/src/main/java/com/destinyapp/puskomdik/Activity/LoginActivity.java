package com.destinyapp.puskomdik.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.destinyapp.puskomdik.API.ApiRequest;
import com.destinyapp.puskomdik.API.RetroServer;
import com.destinyapp.puskomdik.Model.ResponseModel;
import com.destinyapp.puskomdik.R;
import com.destinyapp.puskomdik.SharedPreferance.DB_Helper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    LinearLayout login;
    EditText user,password;
    DB_Helper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DB_Helper(LoginActivity.this);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.linearLogin);
        user = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logic();
            }
        });
    }
    private void Logic(){
        final ProgressDialog pd = new ProgressDialog(LoginActivity.this);
        pd.setMessage("Sedang Mencoba Login");
        pd.show();
        pd.setCancelable(false);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseModel> login =api.login(user.getText().toString(),password.getText().toString());
        login.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if (response.body().getStatusCode().equals("000")){
                        dbHelper.SaveUser(user.getText().toString(),password.getText().toString(),response.body().getData().get(0).getName(),response.body().getData().get(0).getAccessToken(),response.body().getData().get(0).getAs(),response.body().getData().get(0).getPhoto());
                        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                    }
                    pd.hide();
                }catch (Exception e){
                    Toast.makeText(LoginActivity.this, "Terjadi Kesalahan "+e.toString(), Toast.LENGTH_SHORT).show();
                    pd.hide();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                Toast.makeText(LoginActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                Toast.makeText(LoginActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                pd.hide();
                Log.i("Login Logic : ",t.toString());
            }
        });
    }
}