package com.example.android.splendidbandung;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.sql.Time;

public class TimelineFragment extends Fragment {

    private Context context;
    //private static final String TAG = "MainActivity";
        private static final int ADD_DATA_REQUEST_CODE = 4410;
    //    private ViewHolder viewHolder;
    //    private RecyclerView recyclerView;
    //    private RecyclerView.LayoutManager layoutManager;
        private FirebaseRecyclerAdapter firebase;
    //    private CoordinatorLayout coord_main;
        private String ppImageURL, postImageURL;
        private View inflate, view;
    private RecyclerView recyclerView;

    private RecyclerView.LayoutManager layoutManager;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FirebaseApp.initializeApp(context);
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("posts").push();
        recyclerView = (RecyclerView) view.findViewById(R.id.timeline_recycler);
        layoutManager = new LinearLayoutManager(getContext());
        fetch();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getContext();
        view = inflater.inflate(R.layout.fragment_timeline, container, false);
        return view;
    }

    private void fetch() {
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("posts");
        FirebaseRecyclerOptions<TimelineAbstract> options =
                new FirebaseRecyclerOptions.Builder<TimelineAbstract>()
                .setQuery(query, new SnapshotParser<TimelineAbstract>() {
                    @NonNull
                    @Override
                    public TimelineAbstract parseSnapshot(@NonNull DataSnapshot snapshot) {
                        ppImageURL = snapshot.child("image").getValue().toString();
                        return new TimelineAbstract
                                (snapshot.child("nama").getValue().toString(),
                                 snapshot.child("image_pp").getValue().toString(),
                                 snapshot.child("image_post").getValue().toString(),
                                 snapshot.child("isi").getValue().toString(),
                                 snapshot.child("tanggal").getValue().toString());
                    }
                }).build();
        firebase = new FirebaseRecyclerAdapter<TimelineAbstract, ViewHolder>(options) {

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                inflate = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.card_timeline_laporan, viewGroup, false);
                return new ViewHolder(inflate);
            }

            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull TimelineAbstract model) {
                holder.setmNama(model.getName());
                holder.setmTanggal(model.getTanggal());
                holder.setmIsi(model.getDesc());
                if (model.getImagePost() != null) {
                    ImageView imageView = inflate.findViewById(R.id.timeline_imageview_photo);
                    imageView.setVisibility(View.VISIBLE);
                    Picasso.with(getContext())
                            .load(model.getImagePost())
                            .into(holder.getmImagePost());
                }

            }
        };

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(firebase);
    }

    @Override
    public void onStart() {
        super.onStart();
        firebase.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        firebase.stopListening();
    }
}

class ViewHolder extends RecyclerView.ViewHolder {
    private CoordinatorLayout root;
    private TextView mNama, mTanggal, mIsi;
    private ImageView mImagePP, mImagePost;
    private Drawable drawablePP, drawablePost;

    public TextView getmNama() {
        return mNama;
    }

    public void setmNama(String nama) {
        mNama.setText(nama);
    }

    public TextView getmTanggal() {
        return mTanggal;
    }

    public void setmTanggal(String tanggal) {
        mTanggal.setText(tanggal);
    }

    public TextView getmIsi() {
        return mIsi;
    }

    public void setmIsi(String isi) {
        mIsi.setText(isi);
    }

    public ImageView getmImagePP() {
        return mImagePP;
    }

    public void setmImagePP(Drawable drawable) {
        mImagePP.setImageDrawable(drawable);
    }

    public ImageView getmImagePost() {
        return mImagePost;
    }

    public void setmImagePost(Drawable drawable) {
        mImagePost.setImageDrawable(drawable);
    }

    public ViewHolder(@NonNull View itemView) {

        super(itemView);
        root = itemView.findViewById(R.id.coor_timeline);
        mNama = itemView.findViewById(R.id.timeline_textview_nama);
        mTanggal = itemView.findViewById(R.id.timeline_textview_tanggal);
        mIsi = itemView.findViewById(R.id.timeline_textview_isi);
        mImagePP = itemView.findViewById(R.id.timeline_imageview_profile);
        mImagePost = itemView.findViewById(R.id.timeline_imageview_photo);

    }
}

class TimelineAbstract {
    public String name;
    private String imagePP;
    private String imagePost;
    private String desc;
    private String tanggal;

    public TimelineAbstract(String name, String imagePP, String imagePost, String desc, String tanggal) {
        this.name = name;
        this.imagePP = imagePP;
        this.imagePost = imagePost;
        this.desc = desc;
        this.tanggal = tanggal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePP() {
        return imagePP;
    }

    public void setImagePP(String imagePP) {
        this.imagePP = imagePP;
    }

    public String getImagePost() {
        return imagePost;
    }

    public void setImagePost(String imagePost) {
        this.imagePost = imagePost;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}