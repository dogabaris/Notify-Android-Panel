package com.muzkabugu.shadyfade.loginscreen;

import org.json.JSONObject;

import java.util.List;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Headers;
import retrofit.http.POST;

/**
 * Created by shadyfade on 7/31/15.
 */
public interface Services {

    @POST("/loginjson")
        void Login(@Body User user, Callback<User> cb);

    @POST("/create")
        void AddNotify(@Body Notify notify, Callback<Notify> cb);

}
