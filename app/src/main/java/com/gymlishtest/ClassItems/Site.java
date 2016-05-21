package com.gymlishtest.ClassItems;

/**
 * Created by Kévin on 20/05/2016.
 */
public class Site {
    private String nameSite;
    private String overviewSite;
    private int image;
    private String URL;

    public Site() {
    }

    public Site(String nameSite, String overviewSite, int image, String URL) {
        this.nameSite = nameSite;
        this.overviewSite = overviewSite;
        this.image = image;
        this.URL = URL;
    }

    public String getNameSite() {
        return nameSite;
    }

    public void setNameSite(String nameSite) {
        this.nameSite = nameSite;
    }

    public String getOverviewSite() {
        return overviewSite;
    }

    public void setOverviewSite(String overviewSite) {
        this.overviewSite = overviewSite;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
