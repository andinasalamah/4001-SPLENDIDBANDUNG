package com.example.android.splendidbandung.Penginapan;

//package demo.androidpoint.com.androidcostomlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.splendidbandung.R;

import java.util.List;
//import demo.androidpoint.com.androidcostomlist.modal.item;

public class PenginapanAdapter extends RecyclerView.Adapter<PenginapanAdapter.ViewHolder> {
    private List<Penginapan> penginapaan;
    private int rowLayout;
    private Context mContext;
    public PenginapanAdapter(List<Penginapan> penginapaan, int rowLayout, Context context) {
        this.penginapaan = penginapaan;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }
    @Override
    public int getItemCount() {
        return penginapaan == null ? 0 : penginapaan.size();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Penginapan myItem = penginapaan.get(i);
        viewHolder.Name.setText(myItem.getName());
        viewHolder.Image.setImageDrawable(mContext.getDrawable(myItem.getOmg()));
        viewHolder.Alamat.setText(myItem.getAlamat());
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView Name, Alamat;
        public ImageView Image;

        public ViewHolder(View itemView) {
            super(itemView);
            Name = (TextView) itemView.findViewById(R.id.text_name);
            Image = (ImageView)itemView.findViewById(R.id.Image);
            Alamat = (TextView) itemView.findViewById(R.id.text_alamat);
        }
    }
}
