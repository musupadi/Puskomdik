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
import com.destinyapp.puskomdik.Activity.menu.Menu1.AgendaSekolahActivity;
import com.destinyapp.puskomdik.Activity.menu.Menu1.EHadirActivity;
import com.destinyapp.puskomdik.Activity.menu.Menu1.EraportActivity;
import com.destinyapp.puskomdik.Activity.menu.Menu1.Finished.Eskul.EskulActivity;
import com.destinyapp.puskomdik.Activity.menu.Menu1.JadwalPelajaranActivity;
import com.destinyapp.puskomdik.Activity.menu.Menu1.Finished.KabarSekolah.KabarSekolahActivity;
import com.destinyapp.puskomdik.Activity.menu.Menu1.KehadiranActivity;
import com.destinyapp.puskomdik.Activity.menu.Menu1.LokerActivity;
import com.destinyapp.puskomdik.Activity.menu.Menu1.Finished.PPDBActivity;
import com.destinyapp.puskomdik.Activity.menu.Menu1.Finished.PrestasiActivity;
import com.destinyapp.puskomdik.Activity.menu.Menu1.Finished.ProfilSekolahActivity;
import com.destinyapp.puskomdik.Activity.menu.Menu1.Finished.StrukturOraganisasiActivity;
import com.destinyapp.puskomdik.Activity.menu.Menu1.TemanActivity;
import com.destinyapp.puskomdik.Activity.menu.Menu1.TugasActivity;
import com.destinyapp.puskomdik.Activity.menu.Menu1.UjianActivity;
import com.destinyapp.puskomdik.Activity.menu.Menu2.BiayaAkademikActivity;
import com.destinyapp.puskomdik.Activity.menu.Menu2.EWalletActivity;
import com.destinyapp.puskomdik.Activity.menu.Menu2.GalleryActivity;
import com.destinyapp.puskomdik.Activity.menu.Menu2.GraduasiActivity;
import com.destinyapp.puskomdik.Activity.menu.Menu2.GuruActivity;
import com.destinyapp.puskomdik.Activity.menu.Menu2.KoperasiActivity;
import com.destinyapp.puskomdik.Activity.menu.Menu2.MarketplaceActivity;
import com.destinyapp.puskomdik.Activity.menu.Menu2.PaymentActivity;
import com.destinyapp.puskomdik.Activity.menu.Menu2.PerpustakaanOnlineActivity;
import com.destinyapp.puskomdik.Activity.menu.Menu2.RAKSActivity;
import com.destinyapp.puskomdik.Activity.menu.Menu2.TryOutActivity;
import com.destinyapp.puskomdik.Activity.menu.Menu2.VConActivity;
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
    LinearLayout ProfilSekolah,AgendaSekolah,Eskul,Loker,KabarSekolah,KabarBerita,Prestasi,PPDB,StrukturSekolah,JadwalPelajaran,LihatSemua;
    //DIALOG 1
    LinearLayout DProfilSekolah,DAgendaSekolah,DEskul,DLoker,DKabarBerita,DPrestasi,DPPDB,DStrukturSekolah,DJadwalPelajaran,DKehadiran,DEHadir,DTugas,DTeman,DUjian,DEraport;
    //DIALOG 2
    LinearLayout DGuru,DPerpustakaanOnline,DVCon,DTryOut,DMediaPembelajaran,DTeachingFactory,DMarketplace,DGalleri,DGraduasi,DPayment,DEwallet,DRAKS,DBiayaAkademik,DKoperasi;
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
        Eskul = view.findViewById(R.id.linearEskul);
        Loker = view.findViewById(R.id.linearLoker);
        KabarSekolah = view.findViewById(R.id.linearKabarSekolah);
        KabarBerita = view.findViewById(R.id.linearLihatSemuaKabarBerita);
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
        DIALOG2();
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
        DEskul = dialog.findViewById(R.id.linearEskul);
        DLoker = dialog.findViewById(R.id.linearLoker);
        DKabarBerita = dialog.findViewById(R.id.linearKabarSekolah);
        DPrestasi = dialog.findViewById(R.id.linearPrestasi);
        DPPDB = dialog.findViewById(R.id.linearPPDB);
        DStrukturSekolah = dialog.findViewById(R.id.linearStrukturOrganisasi);
        DJadwalPelajaran = dialog.findViewById(R.id.linearJadwalPelajaran);
        DKehadiran = dialog.findViewById(R.id.linearKehadiran);
        DEHadir = dialog.findViewById(R.id.linearEhadir);
        DTugas = dialog.findViewById(R.id.linearTugas);
        DTeman = dialog.findViewById(R.id.linearTeman);
        DUjian = dialog.findViewById(R.id.linearUjian);
        DEraport = dialog.findViewById(R.id.linearERaport);
    }
    private void DIALOG2(){
        DGuru = dialog.findViewById(R.id.linearGuru);
        DPerpustakaanOnline = dialog.findViewById(R.id.linearPerpustakaanOnline);
        DVCon = dialog.findViewById(R.id.linearVCon);
        DTryOut = dialog.findViewById(R.id.linearTryOut);
        DMediaPembelajaran = dialog.findViewById(R.id.linearMediaPembelajaran);
        DTeachingFactory = dialog.findViewById(R.id.linearTeachingFactory);
        DMarketplace = dialog.findViewById(R.id.linearMarketplace);
        DGalleri = dialog.findViewById(R.id.linearGalleri);
        DGraduasi = dialog.findViewById(R.id.linearGraduasi);
        DPayment = dialog.findViewById(R.id.linearPayment);
        DEwallet = dialog.findViewById(R.id.linearEWallet);
        DRAKS = dialog.findViewById(R.id.linearRaks);
        DBiayaAkademik = dialog.findViewById(R.id.linearBiayaAkademik);
        DKoperasi = dialog.findViewById(R.id.linearKoperasi);
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
        DEskul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EskulActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        DLoker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LokerActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        DKabarBerita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), KabarSekolahActivity.class);
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
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), JadwalPelajaranActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        DKehadiran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), KehadiranActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        DEHadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EHadirActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        DTugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TugasActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        DTeman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TemanActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        DUjian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UjianActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        DEraport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EraportActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        DGuru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GuruActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        DPerpustakaanOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PerpustakaanOnlineActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        DVCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), VConActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        DTryOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TryOutActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        DTeachingFactory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        DMarketplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MarketplaceActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        DGalleri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GalleryActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        DGraduasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GraduasiActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        DPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PaymentActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        DEwallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EWalletActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        DRAKS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RAKSActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        DBiayaAkademik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BiayaAkademikActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        DKoperasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), KoperasiActivity.class);
                startActivity(intent);
                getActivity().finish();
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
        Eskul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EskulActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        Loker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LokerActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        KabarSekolah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), KabarSekolahActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        KabarBerita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), KabarSekolahActivity.class);
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
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), JadwalPelajaranActivity.class);
                startActivity(intent);
                getActivity().finish();
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