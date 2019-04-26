package com.example.android.splendidbandung.Belanja;

public class Belanja {
    public String name , alamat;
    public int omg;
    public Belanja(String name, String alamat, int omg) {
        this.name = name;
        this.alamat = alamat;
        this.omg= omg;
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

    public Integer getOmg() {
        return omg;
    }
    public void setOmg(Integer omg) {
        this.omg = omg;
    }

}