package com.entertainment.danielvalencia.diskbar.com.entertainment.tools;

import java.util.Date;

/**
 * Created by daniel.valencia on 15/04/2015.
 */
public class Item {

    private String url_image;
    private String topText;
    private String bottomText;
    private String style;
    private String webpage;
    private String address;
    private Date date;

    public Item (String url_image, String topText, String bottomText, String style, String address, String webpage, Date date) {
        this.url_image = url_image;
        this.topText = topText;
        this.bottomText = bottomText;
        this.address = address;
        this.webpage = webpage;
        this.date = date;
    }

    public String get_topText() {
        return topText;
    }

    public String get_bottomText() {
        return bottomText;
    }

    public String get_url_image() {
        return url_image;
    }

    public String getStyle() {
        return style;
    }

    public Date getDate() {
        return date;
    }

    public String getAddress() {
        return address;
    }

    public String getWebpage() {
        return webpage;
    }
}
