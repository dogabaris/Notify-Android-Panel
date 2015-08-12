package com.muzkabugu.shadyfade.loginscreen;

import retrofit.RestAdapter;

/**
 * Created by shadyfade on 8/3/15.
 */
public class Global {
    public static RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint("http://45.55.202.84")
            .build();

    public static Services service = restAdapter.create(Services.class);
}
