package com.muzkabugu.shadyfade.loginscreen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shadyfade on 8/12/15.
 */
public class Profile {
    private String username;
    private String password;
    private String id;
    public List<Roles> roles;

    public List<Roles> getRoles() {
        return (List<Roles>) roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
