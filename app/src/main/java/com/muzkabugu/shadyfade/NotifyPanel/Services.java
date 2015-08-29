package com.muzkabugu.shadyfade.NotifyPanel;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
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
        void DeleteNotify(@Path("id") String id, Callback<ArrangementResponse> cb);

    @PUT("/posts/{id}")
        void UpdateNotify(@Path("id") String id,@Body Notify notify, Callback<ArrangementResponse> cb);

}
