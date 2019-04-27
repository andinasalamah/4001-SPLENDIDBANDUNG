package com.example.android.splendidbandung;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class HotPlacesAdapter extends RecyclerView.Adapter<HotPlacesAdapter.PlacesViewHolder> {
    private ArrayList<HotPlaces> mDataSet;

    public HotPlacesAdapter(ArrayList mData) {
        this.mDataSet = mData;
    }

    @NonNull
    @Override
    public PlacesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.card_main, viewGroup, false);
        return new PlacesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotPlacesAdapter.PlacesViewHolder placesViewHolder, int i) {
        placesViewHolder.txtNama.setText(mDataSet.get(i).getNama());
        placesViewHolder.txtDesc.setText(mDataSet.get(i).getDesc());
        placesViewHolder.imgIco.setImageResource(mDataSet.get(i).getIcon());
    }

    @Override
    public int getItemCount() {
        return (mDataSet != null) ? mDataSet.size() : 0;
    }

    public class PlacesViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNama, txtDesc;
        private ImageView imgIco;

        public PlacesViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.card_nama);
            txtDesc = (TextView) itemView.findViewById(R.id.card_desc);
            imgIco = (ImageView) itemView.findViewById(R.id.card_ico);

        }
    }
}
