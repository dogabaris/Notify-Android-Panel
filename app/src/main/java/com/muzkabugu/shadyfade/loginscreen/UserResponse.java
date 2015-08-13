package com.muzkabugu.shadyfade.loginscreen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by shadyfade on 8/12/15.
 */
public class UserResponse {
    @SerializedName("Profile")
    @Expose
    private Profile profile;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
