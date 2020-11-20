package com.destinyapp.puskomdik.Activity.menu.Menu1.Finished.Eskul;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.destinyapp.puskomdik.API.ApiRequest;
import com.destinyapp.puskomdik.API.RetroServer;
import com.destinyapp.puskomdik.Method.Destiny;
import com.destinyapp.puskomdik.Model.Eskul.Response;
import com.destinyapp.puskomdik.R;
import com.destinyapp.puskomdik.SharedPreferance.DB_Helper;

import retrofit2.Call;
import retrofit2.Callback;

public class DetailEskulFragment extends Fragment {
    Destiny destiny;
    DB_Helper dbHelper;
    String Username,Password,Nama,Token,Level,Photo;

    //DETAIL Eskul
    String ID_ESKUL,ESKUL,DESKRIPSI,PEMBIMBING,GAMBAR;
    TextView eskul,pembimbing;
    ImageView gambar;
    WebView deskripsi;

    public DetailEskulFragment() {
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
        return inflater.inflate(R.layout.fragment_detail_eskul, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        destiny = new Destiny();
        deskripsi = view.findViewById(R.id.webIsi);
        pembimbing = view.findViewById(R.id.tvPembimbing);
        gambar = view.findViewById(R.id.ivGambar);
        DBHelper();
        Logic();
        ONCLICK();
    }

    private void DBHelper(){
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
    private void ONCLICK(){

    }
    private void Logic(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<Response> Data = api.EskulAll(destiny.AUTH(Token));
        Data.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                try {
                    if (response.body().getStatusCode().equals("000")){
//                        eskul.setText(response.body().getData().get(0).getNama_ekskul());
//                        deskripsi.setText(response.body().getData().get(0).getDeskripsi_ekskul());
                        deskripsi.loadData(response.body().getData().get(0).getDeskripsi_ekskul(),"text/html","UTF-8");
                        pembimbing.setText(response.body().getData().get(0).getPembimbing_ekskul());
                        Glide.with(getActivity())
                                .load(destiny.BASE_URL()+response.body().getData().get(0).getCover_ekskul())
                                .into(gambar);
                    }else if (response.body().getStatusCode().equals("001") || response.body().getStatusCode().equals("002")){
                        destiny.AutoLogin(Username,Password,getActivity());
                        Toast.makeText(getActivity(), "Silahkan Coba Lagi", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "Terjadi Kesalahan ", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Log.i("Error ",e.toString());
                    Toast.makeText(getActivity(), "Terjadi Kesalahan User akan Terlogout", Toast.LENGTH_SHORT).show();
//                    dbHelper.Logout();
//                    Intent intent = new Intent(getActivity(), LoginActivity.class);
//                    startActivity(intent);
//                    getActivity().finish();
                }
            }
            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }
}