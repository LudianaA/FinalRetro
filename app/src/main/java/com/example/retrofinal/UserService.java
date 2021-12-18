package com.example.retrofinal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @GET("users/")
    Call<List<UserResponse>> getAllUsers();

    @GET("users/{id}")
    Call<UserResponse> addUser(@Path("id") int myId);
}
