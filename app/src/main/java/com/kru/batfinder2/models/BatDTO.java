package com.kru.batfinder2.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class BatDTO implements Parcelable {

    @SerializedName("id")
    private int id;

    @SerializedName("commonNameEn")
    private String common_name_en;

    @SerializedName("commonNameNl")
    private String common_name_nl;

    @SerializedName("scientificName")
    private String scientific_name;

    @SerializedName("minBodylength")
    private int min_bodylength;

    @SerializedName("maxBodylength")
    private int max_bodylength;

    @SerializedName("descriptionEn")
    private String description_en;

    @SerializedName("descriptionNl")
    private String description_nl;

    @SerializedName("imageUrl")
    private String image_url;

    @SerializedName("imageAuthorName")
    private String image_credit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommon_name_en() {
        return common_name_en;
    }

    public void setCommon_name_en(String common_name_en) {
        this.common_name_en = common_name_en;
    }

    public String getCommon_name_nl() {
        return common_name_nl;
    }

    public void setCommon_name_nl(String common_name_nl) {
        this.common_name_nl = common_name_nl;
    }

    public String getScientific_name() {
        return scientific_name;
    }

    public void setScientific_name(String scientific_name) {
        this.scientific_name = scientific_name;
    }

    public int getMin_bodylength() {
        return min_bodylength;
    }

    public void setMin_bodylength(int min_bodylength) {
        this.min_bodylength = min_bodylength;
    }

    public int getMax_bodylength() {
        return max_bodylength;
    }

    public void setMax_bodylength(int max_bodylength) {
        this.max_bodylength = max_bodylength;
    }

    public String getDescription_en() {
        return description_en;
    }

    public void setDescription_en(String description_en) {
        this.description_en = description_en;
    }

    public String getDescription_nl() {
        return description_nl;
    }

    public void setDescription_nl(String description_nl) {
        this.description_nl = description_nl;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getImage_credit() {
        return image_credit;
    }

    public void setImage_credit(String image_credit) {
        this.image_credit = image_credit;
    }

    public BatDTO(int id, String common_name_en, String common_name_nl, String scientific_name,
                  int min_bodylength, int max_bodylength, String description_en,
                  String description_nl, String image_url, String image_credit) {
        this.id = id;
        this.common_name_en = common_name_en;
        this.common_name_nl = common_name_nl;
        this.scientific_name = scientific_name;
        this.min_bodylength = min_bodylength;
        this.max_bodylength = max_bodylength;
        this.description_en = description_en;
        this.description_nl = description_nl;
        this.image_url = image_url;
        this.image_credit = image_credit;
    }

    public BatDTO(Parcel source) {
        id = source.readInt();
        common_name_en = source.readString();
        common_name_nl = source.readString();
        scientific_name = source.readString();
        min_bodylength = source.readInt();
        max_bodylength = source.readInt();
        description_en = source.readString();
        description_nl = source.readString();
        image_url = source.readString();
        image_credit = source.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(common_name_en);
        dest.writeString(common_name_nl);
        dest.writeString(scientific_name);
        dest.writeInt(min_bodylength);
        dest.writeInt(max_bodylength);
        dest.writeString(description_en);
        dest.writeString(description_nl);
        dest.writeString(image_url);
        dest.writeString(image_credit);
    }

    public static final Creator<BatDTO> CREATOR =
            new Creator<BatDTO>() {
                @Override
                public BatDTO createFromParcel(Parcel source) {
                    return new BatDTO(source);
                }

                @Override
                public BatDTO[] newArray(int size) {
                    return new BatDTO[size];
                }
            };

}
