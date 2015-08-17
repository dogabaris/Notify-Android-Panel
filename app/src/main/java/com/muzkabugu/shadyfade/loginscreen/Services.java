package com.muzkabugu.shadyfade.loginscreen;

import org.json.JSONObject;

import java.util.List;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by shadyfade on 7/31/15.
 */
public interface Services {

    @POST("/login")
        void Login(@Body User user, Callback<UserResponse> cb);

    @POST("/create")
        void AddNotify(@Body Notify notify, Callback<Notify> cb);

    @GET("/posts/user/{username}")
        void GetNotify(@Path("username") String username, Callback<Posts> cb);

    @DELETE("/posts/{id}")
        void DeleteNotify(@Path("id") String id, Callback<String> cb);

}
