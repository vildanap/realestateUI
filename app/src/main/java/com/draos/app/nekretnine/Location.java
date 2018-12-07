package com.draos.app.nekretnine;

import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("id")
    long id;

    @SerializedName("address")
    String address;
    @SerializedName("settlement")
    String settlement;

    @SerializedName("city_id")
    int city;

    public Location(int id, String address, String settlement, int city) {
        this.id = id;
        this.address = address;
        this.city=city;
    }

    public Location(String address) {
        this.address = address;
    }
}