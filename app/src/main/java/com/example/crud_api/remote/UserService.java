package com.example.crud_api.remote;

import com.example.crud_api.Model.PostModel;
import com.example.crud_api.ResponseParser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {
    @GET("article/18dthd3_nhom1")
    Call<ResponseParser> getPosts();

    @POST("article/")
    Call<ResponseParser> addPost(@Body PostModel postModel);

    @PUT("article/{id}")
    Call<ResponseParser> updatePost(@Path("id") String id, @Body PostModel postModel);

    @DELETE("article/{id}")
    Call<ResponseParser> deletePost(@Path("id") String id);
}
