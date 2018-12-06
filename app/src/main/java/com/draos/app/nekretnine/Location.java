package com.draos.app.nekretnine;

import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("id")
    int id;

    @SerializedName("address")
    String address;

    public Location(int id, String address) {
        this.id = id;
        this.address = address;
    }

    public Location(String address) {
        this.address = address;
    }
}