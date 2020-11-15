package com.destinyapp.puskomdik.Activity.ui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.destinyapp.puskomdik.API.ApiRequest;
import com.destinyapp.puskomdik.API.RetroServer;
import com.destinyapp.puskomdik.Activity.HomeActivity;
import com.destinyapp.puskomdik.Activity.LoginActivity;
import com.destinyapp.puskomdik.Method.Destiny;
import com.destinyapp.puskomdik.Model.ResponseModel;
import com.destinyapp.puskomdik.R;
import com.destinyapp.puskomdik.SharedPreferance.DB_Helper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserFragment extends Fragment {
    Button logout;
    DB_Helper db_helper;
    TextView NISN,NISN2,nama;
    ImageView profile;

    Destiny destiny;

    DB_Helper dbHelper;
    String Username,Password,Nama,Token,Level,Photo;
    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        destiny = new Destiny();
        db_helper = new DB_Helper(getActivity());
        NISN  = view.findViewById(R.id.tvNISN);
        NISN2 = view.findViewById(R.id.tvNISN2);
        nama = view.findViewById(R.id.tvNama);
        profile = view.findViewById(R.id.ivProfile);
        logout = view.findViewById(R.id.btnLogot);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db_helper.Logout();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        dbHelper = new DB_Helper(getActivity());
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
    private void Logic(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseModel> login =api.login(Username,Password);
        login.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if (response.body().getStatusCode().equals("000")){
                        nama.setText(response.body().getData().get(0).getName());
                        NISN.setText(Username);
                        NISN2.setText(Username);
                        Glide.with(getActivity())
                                .load(destiny.BASE_URL()+response.body().getData().get(0).getPhoto())
                                .into(profile);
                        dbHelper.Logout();
                        dbHelper.SaveUser(Username,Password,response.body().getData().get(0).getName(),response.body().getData().get(0).getAccessToken(),response.body().getData().get(0).getAs(),response.body().getData().get(0).getPhoto());
                    }else{
                        Toast.makeText(getActivity(), response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Terjadi Kesalahan "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                Toast.makeText(LoginActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                Log.i("Login Logic : ",t.toString());
            }
        });
    }
}