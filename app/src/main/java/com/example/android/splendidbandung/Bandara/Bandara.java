package com.example.android.splendidbandung.Bandara;

public class Bandara {
    public String name , alamat, detail;
    public int omg;
    public Bandara(String name, String alamat, int omg, String detail) {
        this.name = name;
        this.alamat = alamat;
        this.omg = omg;
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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