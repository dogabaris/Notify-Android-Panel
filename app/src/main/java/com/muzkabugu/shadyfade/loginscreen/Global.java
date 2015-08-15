package com.muzkabugu.shadyfade.loginscreen;

import com.squareup.okhttp.OkHttpClient;


import java.net.CookieManager;
import java.net.CookiePolicy;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by shadyfade on 8/3/15.
 */
public class Global {

    private static Services service;

    public static Services getService() {
        if(service == null) {
            OkHttpClient client = new OkHttpClient();
            CookieManager cookieManager = new CookieManager();
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            client.setCookieHandler(cookieManager);

            OkClient serviceClient = new OkClient(client);

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://45.55.202.84")
                    .setClient(serviceClient)
                    .build();
            service = restAdapter.create(Services.class);
        }
            return service;
    }

}
