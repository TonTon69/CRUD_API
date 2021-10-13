package com.example.crud_api.remote;

import com.example.crud_api.Model.PostModel;

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
    Call<List<PostModel>> getPosts();

    @POST("add/")
    Call<PostModel> addPost(@Body PostModel postModel);

    @PUT("update/{id}")
    Call<PostModel> updatePost(@Path("id") int id, @Body PostModel postModel);

    @DELETE("delete/{id}")
    Call<PostModel> deletePost(@Path("id") int id);
}
