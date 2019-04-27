package com.example.android.splendidbandung;

public class HotPlaces {

    private String nama;
    private int icon;
    private String desc;
    private String loc;

    public HotPlaces(String nama, int icon, String desc) {
        this.nama = nama;
        this.icon = icon;
        this.desc = desc;

    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }


}
