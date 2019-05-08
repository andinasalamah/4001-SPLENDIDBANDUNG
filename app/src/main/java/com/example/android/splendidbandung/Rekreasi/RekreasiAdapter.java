package com.example.android.splendidbandung.Rekreasi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.android.splendidbandung.R;
import java.util.List;

public class RekreasiAdapter extends RecyclerView.Adapter<RekreasiAdapter.ViewHolder> {
    private List<Rekreasi> rekreasist;
    private int rowLayout;
    private Context mContext;
    public RekreasiAdapter(List<Rekreasi> rekreasist, int rowLayout, Context context) {
        this.rekreasist = rekreasist;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }
    @Override
    public int getItemCount() {
        return rekreasist == null ? 0 : rekreasist.size();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Rekreasi myItem = rekreasist.get(i);
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
