package com.example.retrofinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements UsersAdapter.ClickedItem {
    Toolbar toolbar;
    RecyclerView recyclerView;
    UsersAdapter usersAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        usersAdapter = new UsersAdapter(this::ClickedUser);
        getAllUsers();


    }

    public void getAllUsers(){

        Call<List<UserResponse>> userlist = ApiClient.getService().getAllUsers();

        userlist.enqueue(new Callback<List<UserResponse>>() {
            @Override
            public void onResponse(Call<List<UserResponse>> call, Response<List<UserResponse>> response) {

                if(response.isSuccessful()){
                    // binding the list to the adapter
                    List<UserResponse> userResponses = response.body();
                    usersAdapter.setData(userResponses);
                    recyclerView.setAdapter(usersAdapter);

                }

            }

            @Override
            public void onFailure(Call<List<UserResponse>> call, Throwable t) {
                // give message if failed
                Log.e("failed",t.getLocalizedMessage());

            }
        });
    }

    @Override
    public void ClickedUser(UserResponse userResponse) {
        Intent intent = new Intent(this,UserDetailsActivity.class);
        intent.putExtra("data",userResponse.getId());
        startActivity(intent);

    }
}