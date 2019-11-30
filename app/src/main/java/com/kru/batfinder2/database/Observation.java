package com.kru.batfinder2.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "observation_table", foreignKeys = @ForeignKey(entity = Bat.class, parentColumns = "id", childColumns = "batId", onDelete = CASCADE), indices = {@Index("batId")})
public class Observation {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "apiId")
    private int mApiId;

    @ColumnInfo(name = "batId")
    private int mBatId;

    @ColumnInfo(name = "longitude")
    private double mLongitude;

    @ColumnInfo(name = "latitude")
    private double mLatitude;

    public Observation(int apiId, int batId, double longitude, double latitude) {
        mApiId = apiId;
        mBatId = batId;
        mLongitude = longitude;
        mLatitude = latitude;
    }

    public int getId() {
        return mId;
    }

    public int getApiId() {
        return mApiId;
    }

    public void setApiId(int apiId) {
        mApiId = apiId;
    }

    public int getBatId() {
        return mBatId;
    }

    public void setBatId(int batId) {
        mBatId = batId;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        mLongitude = longitude;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        mLatitude = latitude;
    }

    public void setId(int id) {
        mId = id;
    }
}
