package com.muzkabugu.shadyfade.loginscreen;

/**
 * Created by shadyfade on 8/10/15.
 */
public class Notify {
    private String username;
    private String title;
    private String tag;
    private String content;
    private String published_at;

    public Notify(){}

    public Notify(String username, String tag, String title, String content,String published_at) {
        this.username = username;
        this.tag = tag;
        this.title = title;
        this.content = content;
        this.published_at = published_at;
    }


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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return published_at;
    }

    public void setDate(String date) {
        this.published_at = date;
    }
}
