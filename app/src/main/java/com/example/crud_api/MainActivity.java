package com.example.crud_api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.crud_api.Model.PostModel;
import com.example.crud_api.remote.ApiUtils;
import com.example.crud_api.remote.UserService;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    Button btnAddUser;
    Button btnGetUsersList;
    ListView listView;

    UserService userService;
    List<PostModel> list = new ArrayList<PostModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnAddUser = (Button) findViewById(R.id.btnAddPost);
        btnGetUsersList = (Button) findViewById(R.id.btnGetPostsList);
        listView = (ListView) findViewById(R.id.listView);
        userService = ApiUtils.getUserService();

        btnGetUsersList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get users list
                getUsersList();
            }
        });

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PostActivity.class);
                intent.putExtra("user_name", "");
                startActivity(intent);
            }
        });
    }

    public void getUsersList(){
        Call<List<PostModel>> call = userService.getPosts();
        call.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new PostAdapter(MainActivity.this, R.layout.list_post, list));
                    Log.v("log:","dsfdsfsfsdd");
                }
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}