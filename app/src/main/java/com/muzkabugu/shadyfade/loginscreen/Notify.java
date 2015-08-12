package com.muzkabugu.shadyfade.loginscreen;

/**
 * Created by shadyfade on 8/10/15.
 */
public class Notify {
    private String username;
    private String title;
    private String tag;
    private String announcement;
    private String published_at;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public String getDate() {
        return published_at;
    }

    public void setDate(String date) {
        this.published_at = date;
    }
}
