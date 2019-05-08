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

import com.example.android.splendidbandung.Bandara.ListBandara;
//import com.example.android.splendidbandung.Belanja.ListBelanja;
//import com.example.android.splendidbandung.Penginapan.ListPenginapan;
//import com.example.android.splendidbandung.RumahMakan.ListRumahMakan;
//import com.example.android.splendidbandung.TempatIbadah.ListTempatIbadah;
//import com.example.android.splendidbandung.Travel.ListTravel;

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
        switch (v.getId()) {
            case R.id.bandara:
                Intent intent = new Intent(getActivity(), ListBandara.class);
                intent.putExtra("tujuan", "bandara");
                startActivity(intent);
                break;
//            case R.id.belanja:
//                Intent intent1 = new Intent(getActivity(), ListBelanja.class);
//                intent1.putExtra("tujuan", "belanja");
//                startActivity(intent1);
//                break;
//            case R.id.penginapan:
//                Intent intent2 = new Intent(getActivity(), ListPenginapan.class);
//                intent2.putExtra("tujuan", "penginapan");
//                startActivity(intent2);
//                break;
//            case R.id.travel:
//                Intent intent3 = new Intent(getActivity(), ListTravel.class);
//                intent3.putExtra("tujuan", "penginapan");
//                startActivity(intent3);
//                break;
//            case R.id.rmhmakan:
//                Intent intent4 = new Intent(getActivity(), ListRumahMakan.class);
//                intent4.putExtra("tujuan", "rumahmakan");
//                startActivity(intent4);
//                break;
//            case R.id.ibadah:
//                Intent intent5 = new Intent(getActivity(), ListTempatIbadah.class);
//                intent5.putExtra("tujuan", "rumahmakan");
//                startActivity(intent5);
//                break;
//            case R.id.rekreasi:
//                Intent intent = new Intent(getActivity(), MainMenu.class);
//                intent.putExtra("tujuan", "rekreasi");
//                startActivity(intent);
//                break;

//            case R.id.ibadah:
//
//                intent.putExtra("tujuan", "ibadah");
//                startActivity(intent);
//                break;
//            case R.id.travel:
//                intent.putExtra("tujuan", "travel");
//                startActivity(intent);
//                break;
//            case R.id.wisata:
//                intent.putExtra("tujuan", "wisata");
//                startActivity(intent);
//                break;
        }
    }
}
