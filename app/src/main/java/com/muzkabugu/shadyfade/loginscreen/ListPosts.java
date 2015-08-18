package com.muzkabugu.shadyfade.loginscreen;

import java.util.List;

/**
 * Created by shadyfade on 8/18/15.
 */
public class ListPosts {
    private String id;
    private String title;
    private String content;
    private String published_at;
    public PostUser user;
    public Roles tag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublished_at() {
        return published_at;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }

    public PostUser getUser() {
        return user;
    }

    public void setUser(PostUser user) {
        this.user = user;
    }

    public Roles getTag() {
        return tag;
    }

    public void setTag(Roles tag) {
        this.tag = tag;
    }
}
