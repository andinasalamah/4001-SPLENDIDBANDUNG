package com.example.android.splendidbandung.Bandara;

public class Bandara {
    public String name ,email;
    public int omg;
    public Bandara(String name, String email, int omg) {
        this.name = name;
        this.email = email;
        this.omg = omg;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getOmg() {
        return omg;
    }
    public void setOmg(int omg) {
        this.omg = omg;
    }

}