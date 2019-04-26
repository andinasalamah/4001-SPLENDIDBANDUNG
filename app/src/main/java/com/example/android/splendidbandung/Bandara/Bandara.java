package com.example.android.splendidbandung.Bandara;

public class Bandara {
    public String name , alamat;
    public int omg;
    public Bandara(String name, String alamat, int omg) {
        this.name = name;
        this.alamat = alamat;
        this.omg = omg;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAlamat() {
        return alamat;
    }
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public int getOmg() {
        return omg;
    }
    public void setOmg(int omg) {
        this.omg = omg;
    }

}