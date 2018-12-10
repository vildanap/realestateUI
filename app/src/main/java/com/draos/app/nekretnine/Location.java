package com.draos.app.nekretnine;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("id")
    @Expose
    long id;

    @SerializedName("address")
    @Expose
    String address;
    @SerializedName("settlement")
    @Expose
    String settlement;

    @SerializedName("city")
    @Expose
    City city;

    public Location(int id, String address, String settlement, City city) {
        this.id = id;
        this.address = address;
        this.settlement = settlement;
        this.city=city;
    }

    public Location(String address) {
        this.address = address;
    }

    public Location() {}

    public Location(String address, String settelment, City city) {
        this.address=address;
        this.settlement =settelment;
        this.city=city;
    }
}