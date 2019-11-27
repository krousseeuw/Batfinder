package com.kru.batfinder2.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sponsor_table")
public class Sponsor {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "link")
    private String mLink;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "extraInfo")
    private String mExtraInfo;

    @ColumnInfo(name = "logoUrl")
    private String mLogoUrl;

    @ColumnInfo(name = "languageCode")
    private String mLanguageCode;

    public Sponsor(String link, String name, String extraInfo, String logoUrl, String languageCode) {
        mLink = link;
        mName = name;
        mExtraInfo = extraInfo;
        mLogoUrl = logoUrl;
        mLanguageCode = languageCode;
    }

    public int getId() { return mId; }

    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        this.mLink = link;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getExtraInfo() {
        return mExtraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.mExtraInfo = extraInfo;
    }

    public String getLogoUrl() {
        return mLogoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.mLogoUrl = logoUrl;
    }
}
