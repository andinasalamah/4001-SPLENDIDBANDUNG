package com.example.android.splendidbandung;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class LaporanFragment extends Fragment {

    private Context context;
    //private static final String TAG = "MainActivity";
    private static final int ADD_DATA_REQUEST_CODE = 4410;
    //    private ViewHolder viewHolder;
    //    private RecyclerView recyclerView;
    //    private RecyclerView.LayoutManager layoutManager;
    private FirebaseRecyclerAdapter firebase;
    private CoordinatorLayout coord_main;
    private String ppImageURL, postImageURL;
    private View inflate, view;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference childRef;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getContext();
        view = inflater.inflate(R.layout.fragment_laporan, container, false);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().push();
        layoutManager = new LinearLayoutManager(context);
        childRef = mDatabaseRef.child("komplain");
        recyclerView = view.findViewById(R.id.laporan_recycler);
        coord_main = view.findViewById(R.id.coor_laporan);
        mAuth = FirebaseAuth.getInstance();

        fetch();
        FloatingActionButton fab = view.findViewById(R.id.fab_post_lapor);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_data();
            }
        });
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<ReportAbstract> list = new ArrayList<>();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    list.add(snapshot.getValue(ReportAbstract.class));
                    Toast.makeText(getContext(), list.get(0).toString(), Toast.LENGTH_LONG)
                            .show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        return view;
    }

    private void add_data() {
        Intent intent = new Intent(context, NewReportActivity.class);
        startActivity(intent);
    }

    private void fetch() {
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("komplain");

        FirebaseRecyclerOptions<ReportAbstract> options = new FirebaseRecyclerOptions.Builder<ReportAbstract>()
                        .setQuery(query, new SnapshotParser<ReportAbstract>() {

                            @NonNull
                            @Override
                            public ReportAbstract parseSnapshot(@NonNull DataSnapshot snapshot) {

                                if (snapshot.child("image").exists()) {
                                    return new ReportAbstract(snapshot.child("nama").getValue().toString(),
                                            snapshot.child("image").getValue().toString(),
                                            snapshot.child("isi").getValue().toString(),
                                            snapshot.child("tanggal").getValue().toString(),
                                            snapshot.child("jam").getValue().toString());
                                }
                                return new ReportAbstract(
                                        snapshot.child("nama").getValue().toString(),
                                        snapshot.child("isi").getValue().toString(),
                                        snapshot.child("tanggal").getValue().toString(),
                                        snapshot.child("jam").getValue().toString());
                            }
                        }).build();

        firebase = new FirebaseRecyclerAdapter<ReportAbstract, ReportViewHolder>(options) {

            @NonNull
            @Override
            public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                inflate = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.card_timeline_laporan, viewGroup, false);
                return new ReportViewHolder(inflate);
            }

            @Override
            protected void onBindViewHolder(@NonNull ReportViewHolder holder, int position, @NonNull ReportAbstract model) {

                holder.setmNama(model.getName());
                holder.setmTanggal(model.getTanggal());
                holder.setmIsi(model.getDesc());
                holder.setmJam(model.getJam());
                Uri user = mAuth.getCurrentUser().getPhotoUrl();
                Picasso.with(context)
                        .load(user)
                        .into(holder.getmImagePP());
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

class ReportViewHolder extends RecyclerView.ViewHolder {
    private CoordinatorLayout root;
    private TextView mNama, mTanggal, mIsi, mJam;
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

    public ReportViewHolder(@NonNull View itemView) {

        super(itemView);
        root = itemView.findViewById(R.id.coor_timeline);
        mNama = itemView.findViewById(R.id.timeline_textview_nama);
        mTanggal = itemView.findViewById(R.id.timeline_textview_tanggal);
        mIsi = itemView.findViewById(R.id.timeline_textview_isi);
        mImagePP = itemView.findViewById(R.id.timeline_imageview_profile);
        mImagePost = itemView.findViewById(R.id.timeline_imageview_photo);
        mJam = itemView.findViewById(R.id.timeline_textview_jam);
    }

    public TextView getmJam() {
        return mJam;
    }

    public void setmJam(String jam) {
        mJam.setText(jam);
    }
}

class ReportAbstract {
    public String name;
    private String imagePP;
    private String imagePost;
    private String desc;
    private String tanggal;
    private String jam;

    public ReportAbstract(String name, String desc, String tanggal, String jam) {
        this.name = name;
        this.desc = desc;
        this.tanggal = tanggal;
        this.jam = jam;
    }

    public ReportAbstract(String name, String imagePost, String desc, String tanggal, String jam) {
        this.name = name;
        this.jam = jam;
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

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }
}
