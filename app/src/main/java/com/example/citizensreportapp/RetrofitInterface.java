package com.example.citizensreportapp;

import org.w3c.dom.Comment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface RetrofitInterface {

    @POST("/login")
    Call<Login> executeLogin(@Body HashMap<String, String> map);

    @POST("/register")
    Call<Void> executeRegister(@Body HashMap<String, String> map);

    @POST("/create")
    Call<Void> executeCreate(@Body HashMap<String, String> map);

//    @GET("/retrievePostData")
//    Call<Post> retrievePosts(@Body HashMap<String, String> map);

    @GET("/retrievePostData")
    Call<List<Post>> retrievePosts(
      @Query("title") String fTitle,
      @Query("text") String fText
    );

    @GET("posts")
    Call<List<Post>> getPosts(@QueryMap Map<String, String> parameters);

    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId);

    @GET
    Call<List<Comment>> getComments(@Url String url);

    @POST("posts")
    Call<Post> createPost(@Body Post post);


}