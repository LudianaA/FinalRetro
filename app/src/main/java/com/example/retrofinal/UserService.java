package com.example.retrofinal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {
    @GET("users/")
    Call<List<UserResponse>> getAllUsers();
    @POST("users/")
    Call<UserResponse> addUser();
}
