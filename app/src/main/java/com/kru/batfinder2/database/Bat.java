package com.kru.batfinder2.database;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class Bat {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "apiId")
    private int mApiId;

    @ColumnInfo(name = "commonNameEn")
    private String mCommonNameEn;

    @ColumnInfo(name = "commonNameNl")
    private String mCommonNameNl;

    @ColumnInfo(name = "scientificName")
    private String mScientificName;

    @ColumnInfo(name = "minBodylength")
    private int mMinBodyLength;

    @ColumnInfo(name = "maxBodylength")
    private int mMaxBodyLength;

    @ColumnInfo(name = "descriptionEn")
    private String mDescriptionEn;

    @ColumnInfo(name = "descriptionNl")
    private String mDescriptionNl;

    @ColumnInfo(name = "imageUrl")
    private String mImageUrl;

    @ColumnInfo(name = "imageAuthorName")
    private String mImageAuthorName;

    public Bat(int apiId, String commonNameEn, String commonNameNl, String scientificName,
               int minBodyLength, int maxBodyLength, String descriptionEn, String descriptionNl,
               String imageUrl, String imageAuthorName) {
        mApiId = apiId;
        mCommonNameEn = commonNameEn;
        mCommonNameNl = commonNameNl;
        mScientificName = scientificName;
        mMinBodyLength = minBodyLength;
        mMaxBodyLength = maxBodyLength;
        mDescriptionEn = descriptionEn;
        mDescriptionNl = descriptionNl;
        mImageUrl = imageUrl;
        mImageAuthorName = imageAuthorName;
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

    public String getCommonNameEn() {
        return mCommonNameEn;
    }

    public void setCommonNameEn(String commonNameEn) {
        mCommonNameEn = commonNameEn;
    }

    public String getCommonNameNl() {
        return mCommonNameNl;
    }

    public void setCommonNameNl(String commonNameNl) {
        mCommonNameNl = commonNameNl;
    }

    public String getScientificName() {
        return mScientificName;
    }

    public void setScientificName(String scientificName) {
        mScientificName = scientificName;
    }

    public int getMinBodyLength() {
        return mMinBodyLength;
    }

    public void setMinBodyLength(int minBodyLength) {
        mMinBodyLength = minBodyLength;
    }

    public int getMaxBodyLength() {
        return mMaxBodyLength;
    }

    public void setMaxBodyLength(int maxBodyLength) {
        mMaxBodyLength = maxBodyLength;
    }

    public String getDescriptionEn() {
        return mDescriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        mDescriptionEn = descriptionEn;
    }

    public String getDescriptionNl() {
        return mDescriptionNl;
    }

    public void setDescriptionNl(String descriptionNl) {
        mDescriptionNl = descriptionNl;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public String getImageAuthorName() {
        return mImageAuthorName;
    }

    public void setImageAuthorName(String imageAuthorName) {
        mImageAuthorName = imageAuthorName;
    }
}
