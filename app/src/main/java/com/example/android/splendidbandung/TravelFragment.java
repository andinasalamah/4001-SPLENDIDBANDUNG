package com.example.android.splendidbandung;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class TravelFragment extends Fragment implements View.OnClickListener{
    ImageButton bandara, belanja, penginapan, rekreasi, rmhmakan, ibadah, travel, wisata;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_travel, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        bandara = view.findViewById(R.id.bandara);
        penginapan = view.findViewById(R.id.penginapan);
        belanja = view.findViewById(R.id.belanja);
        rekreasi = view.findViewById(R.id.rekreasi);
        rmhmakan = view.findViewById(R.id.rmhmakan);
        ibadah = view.findViewById(R.id.ibadah);
        travel = view.findViewById(R.id.travel);
        wisata = view.findViewById(R.id.wisata);

        bandara.setOnClickListener(this);
        penginapan.setOnClickListener(this);
        belanja.setOnClickListener(this);
        rekreasi.setOnClickListener(this);
        rmhmakan.setOnClickListener(this);
        ibadah.setOnClickListener(this);
        travel.setOnClickListener(this);
        wisata.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), MapsActivity.class);
        switch (v.getId()) {
            case R.id.bandara:
                intent.putExtra("tujuan", "bandara");
                startActivity(intent);
                break;
            case R.id.belanja:
                intent.putExtra("tujuan", "belanja");
                startActivity(intent);
                break;
            case R.id.penginapan:
                intent.putExtra("tujuan", "penginapan");
                startActivity(intent);
                break;
            case R.id.rekreasi:
                intent.putExtra("tujuan", "rekreasi");
                startActivity(intent);
                break;
            case R.id.rmhmakan:
                intent.putExtra("tujuan", "rumahmakan");
                startActivity(intent);
                break;
            case R.id.ibadah:
                intent.putExtra("tujuan", "ibadah");
                startActivity(intent);
                break;
            case R.id.travel:
                intent.putExtra("tujuan", "travel");
                startActivity(intent);
                break;
            case R.id.wisata:
                intent.putExtra("tujuan", "wisata");
                startActivity(intent);
                break;
        }
    }
}
