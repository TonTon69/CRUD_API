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
    ListView listView;

    UserService userService;
    List<PostModel> list = new ArrayList<PostModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddUser = (Button) findViewById(R.id.btnAddPost);
        listView = (ListView) findViewById(R.id.listView);
        userService = ApiUtils.getUserService();
        getUsersList();

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PostActivity.class);
                intent.putExtra("group_name", "");
                startActivity(intent);
            }
        });
    }

    public void getUsersList(){
        Call<ResponseParser> call = userService.getPosts();
        call.enqueue(new Callback<ResponseParser>() {
            @Override
            public void onResponse(Call<ResponseParser> call, Response<ResponseParser> response) {
                if(response.isSuccessful()){
                    list = response.body().getData();
                    listView.setAdapter(new PostAdapter(MainActivity.this, R.layout.list_post, list));
                }
            }

            @Override
            public void onFailure(Call<ResponseParser> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}