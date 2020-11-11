package com.destinyapp.puskomdik.Activity.ui;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.destinyapp.puskomdik.API.ApiRequest;
import com.destinyapp.puskomdik.API.RetroServer;
import com.destinyapp.puskomdik.Activity.Adapter.AdapterKabarBerita;
import com.destinyapp.puskomdik.Activity.Adapter.AdapterKegiatan;
import com.destinyapp.puskomdik.Activity.LoginActivity;
import com.destinyapp.puskomdik.Activity.menu.AgendaSekolahActivity;
import com.destinyapp.puskomdik.Activity.menu.PPDBActivity;
import com.destinyapp.puskomdik.Activity.menu.PrestasiActivity;
import com.destinyapp.puskomdik.Activity.menu.ProfilSekolahActivity;
import com.destinyapp.puskomdik.Activity.menu.StrukturOraganisasiActivity;
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


public class HomeFragment extends Fragment {

    Switch SwitchMasuk;
    TextView CheckMasuk;
    LinearLayout ProfilSekolah,AgendaSekolah,Prestasi,PPDB,StrukturSekolah,JadwalPelajaran,Evadir,MediaPembelajaran,Tugas,LihatSemua;
    LinearLayout DProfilSekolah,DAgendaSekolah,DPrestasi,DPPDB,DStrukturSekolah,DJadwalPelajaran,DEvadir,DMediaPembelajaran,DTugas,DGuru,DBiayaAkademik,DPembayaran,DROB;
    //Dialog
    Dialog dialog;
    Button Kembali;
    Destiny destiny;
    TextView nama,namaSiswa;
    DB_Helper dbHelper;
    String Username,Password,Nama,Token,Level,Photo;
    RecyclerView recycler,recyclerKabar;
    private List<DataModel> mItems = new ArrayList<>();
    private List<DataModel> mItems2 = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    public HomeFragment() {
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        destiny = new Destiny();
        recycler = view.findViewById(R.id.recyclerHeader);
        recyclerKabar = view.findViewById(R.id.recyclerKabarBerita);
        nama = view.findViewById(R.id.tvNama);
        namaSiswa = view.findViewById(R.id.tvNamaSiswa);
        SwitchMasuk = view.findViewById(R.id.switchMasuk);
        CheckMasuk = view.findViewById(R.id.tvCheckMasuk);
        ProfilSekolah = view.findViewById(R.id.linearProfilSekolah);
        AgendaSekolah = view.findViewById(R.id.linearAgendaSekolah);
        Prestasi = view.findViewById(R.id.linearPrestasi);
        PPDB = view.findViewById(R.id.linearPPDB);
        StrukturSekolah = view.findViewById(R.id.linearStrukturOrganisasi);
        JadwalPelajaran = view.findViewById(R.id.linearJadwalPelajaran);
        Evadir = view.findViewById(R.id.linearEvadir);
        MediaPembelajaran = view.findViewById(R.id.linearMediaPembelajaran);
        Tugas = view.findViewById(R.id.linearTugas);
        LihatSemua = view.findViewById(R.id.linearLihatSemua);
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_menu_all);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.btn_rounded_white);

        Kembali = dialog.findViewById(R.id.btnKembali);
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
        nama.setText("Halo, "+Nama);
        namaSiswa.setText(Nama);
        DIALOG();
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
        ONCLICK();
        ONCLICKDIALOG();
        Header();
        KabarBerita();
    }
    private void Header(){
        mManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        recycler.setLayoutManager(mManager);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> KabarBerita = api.KabarSekolah(destiny.AUTH(Token));
        KabarBerita.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if (response.body().getStatusCode().equals("000")){
                        mItems=response.body().getData();
                        mAdapter = new AdapterKegiatan(getActivity(),mItems);
                        recycler.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }else if (response.body().getStatusCode().equals("001") || response.body().getStatusCode().equals("002")){
                        destiny.AutoLogin(Username,Password,getActivity());
                        Header();
                    }else{
                        Toast.makeText(getActivity(), "Terjadi Kesalahan ", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Terjadi Kesalahan User akan Terlogout", Toast.LENGTH_SHORT).show();
                    dbHelper.Logout();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void KabarBerita(){
        mManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        recyclerKabar.setLayoutManager(mManager);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> KabarBerita = api.KabarSekolah(destiny.AUTH(Token));
        KabarBerita.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    if (response.body().getStatusCode().equals("000")){
                        mItems=response.body().getData();
                        mAdapter = new AdapterKabarBerita(getActivity(),mItems);
                        recyclerKabar.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }else if (response.body().getStatusCode().equals("001") || response.body().getStatusCode().equals("002")){
                        destiny.AutoLogin(Username,Password,getActivity());
                        KabarBerita();
                    }else{
                        Toast.makeText(getActivity(), "Terjadi Kesalahan ", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Terjadi Kesalahan User akan Terlogout", Toast.LENGTH_SHORT).show();
                    dbHelper.Logout();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void DIALOG(){
        DProfilSekolah = dialog.findViewById(R.id.linearProfilSekolah);
        DAgendaSekolah = dialog.findViewById(R.id.linearAgendaSekolah);
        DPrestasi = dialog.findViewById(R.id.linearPrestasi);
        DPPDB = dialog.findViewById(R.id.linearPPDB);
        DStrukturSekolah = dialog.findViewById(R.id.linearStrukturOrganisasi);
        DJadwalPelajaran = dialog.findViewById(R.id.linearJadwalPelajaran);
        DEvadir = dialog.findViewById(R.id.linearEvadir);
        DMediaPembelajaran = dialog.findViewById(R.id.linearMediaPembelajaran);
        DTugas = dialog.findViewById(R.id.linearTugas);
        DGuru = dialog.findViewById(R.id.linearGuru);
        DBiayaAkademik = dialog.findViewById(R.id.linearBiayaAkademik);
        DPembayaran = dialog.findViewById(R.id.linearPembayaran);
        DROB = dialog.findViewById(R.id.linearROB);
    }
    private void ONCLICKDIALOG(){
        DProfilSekolah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfilSekolahActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        DAgendaSekolah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AgendaSekolahActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        DPrestasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PrestasiActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        DPPDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PPDBActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        DStrukturSekolah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StrukturOraganisasiActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        DJadwalPelajaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Jadwal Pelajaran");
            }
        });
        DEvadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Evadir");
            }
        });
        DMediaPembelajaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Media Pembelajaran");
            }
        });
        DTugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Tugas");
            }
        });
        DGuru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Tugas");
            }
        });
        DGuru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Guru");
            }
        });
        DBiayaAkademik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Biaya Akademik");
            }
        });
        DPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Pembayaran");
            }
        });
        DROB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"ROB");
            }
        });
    }
    private void ONCLICK(){
        ProfilSekolah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfilSekolahActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        AgendaSekolah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AgendaSekolahActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        Prestasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PrestasiActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        PPDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PPDBActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        StrukturSekolah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StrukturOraganisasiActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        JadwalPelajaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Jadwal Pelajaran");
            }
        });
        Evadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Evadir");
            }
        });
        MediaPembelajaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Media Pembelajaran");
            }
        });
        Tugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destiny.ChangeActivity(getActivity(),"Tugas");
            }
        });
        LihatSemua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
        Kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.hide();
            }
        });
    }
}