package com.destinyapp.puskomdik.Activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.destinyapp.puskomdik.Method.Destiny;
import com.destinyapp.puskomdik.Model.Eskul.Eskul;
import com.destinyapp.puskomdik.R;
import com.destinyapp.puskomdik.SharedPreferance.DB_Helper;

import java.util.List;

public class AdapterAnggotaEskul extends RecyclerView.Adapter<AdapterAnggotaEskul.HolderData>  {
    private List<Eskul> mList;
    private Context ctx;

    DB_Helper dbHelper;
    Boolean onClick=false;
    RecyclerView recyclerView;
    Destiny destiny;
    public AdapterAnggotaEskul(Context ctx, List<Eskul> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_anggota_eskul,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, int posistion) {
        destiny = new Destiny();
        final Eskul dm = mList.get(posistion);
        if (dm.getAnggota().size()>1){
            holderData.No.setText(String.valueOf(posistion));
            holderData.Nama.setText(dm.getNama_siswa());
            holderData.Kelas.setText(dm.getNama_kelas());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{ ;
        TextView No,Nama,Kelas;
        LinearLayout card;
        public HolderData(View v){
            super(v);
            No = v.findViewById(R.id.tvNoEskul);
            Nama = v.findViewById(R.id.tvNamaAnggota);
            Kelas = v.findViewById(R.id.tvNamaKelas);
            card = v.findViewById(R.id.LayoutCardView);
        }
    }
}