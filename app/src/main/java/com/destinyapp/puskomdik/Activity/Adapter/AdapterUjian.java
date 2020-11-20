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
import com.destinyapp.puskomdik.Model.DataModel;
import com.destinyapp.puskomdik.R;
import com.destinyapp.puskomdik.SharedPreferance.DB_Helper;

import java.util.List;

public class AdapterUjian extends RecyclerView.Adapter<AdapterUjian.HolderData>  {
    private List<DataModel> mList;
    private Context ctx;

    DB_Helper dbHelper;
    Boolean onClick=false;
    RecyclerView recyclerView;
    Destiny destiny;
    public AdapterUjian(Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_ujian,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, int posistion) {
        destiny = new Destiny();
        final DataModel dm = mList.get(posistion);
        holderData.Mapel.setText(dm.getNama_mapel());
        holderData.Guru.setText(dm.getNama_hari()+", "+destiny.MagicDateChange(dm.getTgl_ujian()));
        holderData.Mulai.setText(dm.getJam_mulai());
        holderData.Selesai.setText(dm.getJam_selesai());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{ ;
        TextView Mapel,Guru,Mulai,Selesai;
        LinearLayout card;
        public HolderData(View v){
            super(v);
            Mapel = v.findViewById(R.id.tvNamaMapel);
            Guru = v.findViewById(R.id.tvNamaGuru);
            Mulai = v.findViewById(R.id.tvMulai);
            Selesai = v.findViewById(R.id.tvSelesai);
            card = v.findViewById(R.id.LayoutCardView);
        }
    }
}


