package com.example.android.splendidbandung.Bandara;

//package demo.androidpoint.com.androidcostomlist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.splendidbandung.R;

import java.util.List;
//import demo.androidpoint.com.androidcostomlist.modal.item;

public class BandaraAdapter extends RecyclerView.Adapter<BandaraAdapter.ViewHolder> {
    private List<Bandara> countries;
    private int rowLayout;
    private Context mContext;
    public BandaraAdapter(List<Bandara> countries, int rowLayout, Context context) {
        this.countries = countries;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }
    @Override
    public int getItemCount() {
        return countries == null ? 0 : countries.size();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final Bandara myItem = countries.get(i);
        viewHolder.Name.setText(myItem.getName());
        viewHolder.Image.setImageDrawable(mContext.getDrawable(myItem.getOmg()));
        viewHolder.Alamat.setText(myItem.getAlamat());
        viewHolder.Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView Name,Alamat;
        public ImageView Image;


        public ViewHolder(View itemView) {
            super(itemView);
            Name = (TextView) itemView.findViewById(R.id.text_name);
            Image = (ImageView)itemView.findViewById(R.id.Image);
            Alamat = (TextView) itemView.findViewById(R.id.text_alamat);

//            Image.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(v.getContext(), DetailBandara.class);
//                    intent.putExtra("image", countries );
//                    intent.putExtra("Detail", myItem.getDetail());
//                    intent.putExtra("nama", myItem.getName());
//                }
//            });
        }
    }
}
