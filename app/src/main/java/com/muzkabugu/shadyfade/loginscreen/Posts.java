package com.muzkabugu.shadyfade.loginscreen;

import java.util.List;

/**
 * Created by shadyfade on 8/17/15.
 */
public class Posts {
    private String id;
    private String title;
    private String content;
    private String published_at;
    public List<PostUser> postUser;
    public List<Roles> roles;

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

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public List<PostUser> getPostuser() {
        return postUser;
    }

    public void setPostuser(List<PostUser> postuser) {
        this.postUser = postuser;
    }

    public String getPublished_at() {
        return published_at;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }
}
