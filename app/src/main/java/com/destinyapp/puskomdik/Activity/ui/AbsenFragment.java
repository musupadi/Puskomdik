package com.destinyapp.puskomdik.Activity.ui;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.destinyapp.puskomdik.Method.Destiny;
import com.destinyapp.puskomdik.R;
import com.destinyapp.puskomdik.SharedPreferance.DB_Helper;


public class AbsenFragment extends Fragment {
    Switch SwitchMasuk;
    TextView CheckMasuk;
    Destiny destiny;
    TextView namaSiswa;
    ImageView ProfileBig,Profile;
    DB_Helper dbHelper;
    String Username,Password,Nama,Token,Level,Photo;
    public AbsenFragment() {
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
        return inflater.inflate(R.layout.fragment_absen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        destiny = new Destiny();
        SwitchMasuk = view.findViewById(R.id.switchMasuk);
        CheckMasuk = view.findViewById(R.id.tvCheckMasuk);
        namaSiswa = view.findViewById(R.id.tvNamaSiswa);
        Profile = view.findViewById(R.id.ivProfile);
        ProfileBig = view.findViewById(R.id.ivProfileBig);
        if (SwitchMasuk.isChecked()){
            CheckMasuk.setText("Masuk");
        }else{
            CheckMasuk.setText("Tidak\nMasuk");
        }
        SwitchMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SwitchMasuk.isChecked()){
                    CheckMasuk.setText("Masuk");
                }else{
                    CheckMasuk.setText("Tidak\nMasuk");
                }
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
        namaSiswa.setText(Nama);
        Glide.with(this)
                .load(destiny.BASE_URL()+Photo)
                .into(Profile);
        Glide.with(this)
                .load(destiny.BASE_URL()+Photo)
                .into(ProfileBig);
    }
}