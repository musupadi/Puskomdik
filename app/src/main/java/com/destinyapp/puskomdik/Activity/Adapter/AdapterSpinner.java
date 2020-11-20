package com.destinyapp.puskomdik.Activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.destinyapp.puskomdik.Model.DataModel;
import com.destinyapp.puskomdik.R;

import java.util.List;

public class AdapterSpinner extends ArrayAdapter<DataModel> {
    public AdapterSpinner(Context context, List<DataModel> list) {
        super(context, 0, list);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }
    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_spinner, parent, false
            );
        }

        TextView textSpinner = convertView.findViewById(R.id.tvSpinner);
        TextView textId = convertView.findViewById(R.id.tvId);
        DataModel currentItem = getItem(position);

        if (currentItem != null) {
            textSpinner.setText(currentItem.getNama_kelas());
            textId.setText(currentItem.getId_kelas());
        }

        return convertView;
    }
}
