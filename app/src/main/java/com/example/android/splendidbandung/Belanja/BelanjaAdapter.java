package com.example.android.splendidbandung.Belanja;

//package demo.androidpoint.com.androidcostomlist;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import com.example.android.splendidbandung.R;
//import demo.androidpoint.com.androidcostomlist.modal.item;

public class BelanjaAdapter extends RecyclerView.Adapter<BelanjaAdapter.ViewHolder> {
    private List<Belanja> belanjaan;
    private int rowLayout;
    private Context mContext;
    public BelanjaAdapter(List<Belanja> belanjaan, int rowLayout, Context context) {
        this.belanjaan = belanjaan;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }
    @Override
    public int getItemCount() {
        return belanjaan == null ? 0 : belanjaan.size();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Belanja myItem = belanjaan.get(i);
        viewHolder.Name.setText(myItem.getName());
        viewHolder.Image.setImageDrawable(mContext.getDrawable(myItem.getOmg()));
        viewHolder.email.setText(myItem.getEmail());
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView Name,email;
        public ImageView Image;

        public ViewHolder(View itemView) {
            super(itemView);
            Name = (TextView) itemView.findViewById(R.id.text_name);
            Image = (ImageView)itemView.findViewById(R.id.Image);
            email = (TextView) itemView.findViewById(R.id.text_email);
        }
    }
}
