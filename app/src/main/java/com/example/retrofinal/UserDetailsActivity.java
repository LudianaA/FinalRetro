package com.example.retrofinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetailsActivity extends AppCompatActivity {

    TextView username,email,phone,website;
    UserResponse userResponse;
    RecyclerView recyclerView;
    UsersAdapter usersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        username  = findViewById(R.id.username);
        email  = findViewById(R.id.email);
        phone  = findViewById(R.id.phone);
        website = findViewById(R.id.website);

        Intent intent = getIntent();
        if(intent.getExtras() !=null){
            userResponse = (UserResponse) intent.getSerializableExtra("data");

            String usernamedata = userResponse.getUsername();
            String useremail = userResponse.getEmail();
            String userPhone = userResponse.getNumber();
            String userWebsite = userResponse.getWebsite();


            username.setText(usernamedata);
            email.setText(useremail);
            phone.setText(userPhone);
            website.setText(userWebsite);



        }

    }


}