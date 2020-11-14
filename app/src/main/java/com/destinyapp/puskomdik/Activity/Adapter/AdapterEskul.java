package com.destinyapp.puskomdik.Activity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.destinyapp.puskomdik.Activity.menu.Menu1.Finished.Eskul.DetailEskulActivity;
import com.destinyapp.puskomdik.Activity.menu.Menu1.Finished.KabarSekolah.DetailKabarSekolahActivity;
import com.destinyapp.puskomdik.Method.Destiny;
import com.destinyapp.puskomdik.Model.DataModel;
import com.destinyapp.puskomdik.R;
import com.destinyapp.puskomdik.SharedPreferance.DB_Helper;

import java.util.List;

public class AdapterEskul extends RecyclerView.Adapter<AdapterEskul.HolderData> {
    private List<DataModel> mList;
    private Context ctx;

    DB_Helper dbHelper;
    Boolean onClick=false;
    RecyclerView recyclerView;
    Destiny destiny;
    public AdapterEskul(Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_eskul,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, int posistion) {
        destiny = new Destiny();
        final DataModel dm = mList.get(posistion);
        holderData.Judul.setText(dm.getNama_ekskul());
//        holderData.Deskripsi.setText(destiny.SmallDescription(dm.getIsi_kabar()));
//        holderData.Tanggal.setText(dm.getCreated_at_kabar());
        Glide.with(ctx)
                .load(destiny.BASE_URL()+dm.getCover_ekskul())
                .into(holderData.Image);
        holderData.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ctx, DetailEskulActivity.class);
                i.putExtra("ESKUL", dm.getNama_ekskul());
                i.putExtra("DESKRIPSI",dm.getDeskripsi_ekskul());
                i.putExtra("PEMBIMBING",dm.getPembimbing_ekskul());
                i.putExtra("ID_ESKUL",dm.getId_ekskul());
                i.putExtra("GAMBAR",destiny.BASE_URL()+dm.getCover_ekskul());
                ctx.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        ImageView Image;
        TextView Judul;
        LinearLayout card;
        public HolderData(View v){
            super(v);
            Image = v.findViewById(R.id.ivGambar);
            Judul = v.findViewById(R.id.tvNamaEskul);
            card = v.findViewById(R.id.LayoutCardView);
        }
    }
}
