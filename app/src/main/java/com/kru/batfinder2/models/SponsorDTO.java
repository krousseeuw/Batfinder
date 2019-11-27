package com.kru.batfinder2.models;

public class SponsorDTO {

    private String link;
    private String name;
    private String extraInfo;
    private String logoUrl;

    public SponsorDTO(String link, String name, String extraInfo, String logoUrl) {
        this.link = link;
        this.name = name;
        this.extraInfo = extraInfo;
        this.logoUrl = logoUrl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
