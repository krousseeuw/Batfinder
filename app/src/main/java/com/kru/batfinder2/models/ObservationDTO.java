package com.kru.batfinder2.models;

import com.google.gson.annotations.SerializedName;

public class ObservationDTO {
    @SerializedName("id")
    private int id;

    @SerializedName("longitude")
    private double longitude;

    @SerializedName("latitude")
    private double latitude;

    @SerializedName("batId")
    private int batId;

    public ObservationDTO(int id, double longitude, double latitude, int batId) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.batId = batId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getBatId() {
        return batId;
    }

    public void setBatId(int batId) {
        this.batId = batId;
    }
}
