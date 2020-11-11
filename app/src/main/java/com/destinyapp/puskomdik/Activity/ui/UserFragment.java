package com.destinyapp.puskomdik.Activity.ui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.destinyapp.puskomdik.Activity.LoginActivity;
import com.destinyapp.puskomdik.Method.Destiny;
import com.destinyapp.puskomdik.R;
import com.destinyapp.puskomdik.SharedPreferance.DB_Helper;

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
        nama.setText(Nama);
        NISN.setText(Username);
        NISN2.setText(Username);
        Glide.with(this)
                .load(destiny.BASE_URL()+Photo)
                .into(profile);
    }
}