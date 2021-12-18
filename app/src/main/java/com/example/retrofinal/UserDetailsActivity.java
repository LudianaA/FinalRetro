package com.example.retrofinal;

import static java.lang.Integer.parseInt;

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

        // Intent intent = getIntent();
        String myId = getIntent().getStringExtra("data");
        addUser(myId);

        /**
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
         **/

    }
    public void addUser(String myId){

        Call<UserResponse> userlist = ApiClient.getService().addUser(parseInt(myId));

        userlist.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                if(response.isSuccessful()){
                    // binding the list to the adapter
                    UserResponse userResponses = response.body();
                    String usernamedata = userResponses.getUsername();
                    String useremail = userResponses.getEmail();
                    String userPhone = userResponses.getNumber();
                    String userWebsite = userResponses.getWebsite();


                    username.setText(usernamedata);
                    email.setText(useremail);
                    phone.setText(userPhone);
                    website.setText(userWebsite);


                }

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                // give message if failed
                Log.e("failed",t.getLocalizedMessage());

            }
        });
    }


}