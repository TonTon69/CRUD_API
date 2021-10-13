package com.example.crud_api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crud_api.Model.PostModel;
import com.example.crud_api.remote.ApiUtils;
import com.example.crud_api.remote.UserService;

import org.chromium.base.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {

    UserService userService;
    EditText edtGroupName;
    EditText edtTitle;
    EditText edtContent;
    EditText edtImgUrl;
    Button btnSave;
    Button btnDel;
    TextView txtGroupName;
    TextView txtTitle;
    TextView txtContent;
    TextView txtImgUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        setTitle("Posts");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        txtTitle =(TextView) findViewById(R.id.txtTitle);
        txtContent =(TextView) findViewById(R.id.txtContent);
        txtImgUrl =(TextView) findViewById(R.id.txtImgUrl);

        edtGroupName = (EditText) findViewById(R.id.edtGroupName);
        edtTitle = (EditText) findViewById(R.id.edtTitle);
        edtContent = (EditText) findViewById(R.id.edtContent);
        edtImgUrl = (EditText) findViewById(R.id.edtImgUrl);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);

        userService = ApiUtils.getUserService();

        Bundle extras = getIntent().getExtras();
        final String groupName = extras.getString("groupName");
        String title = extras.getString("title");
        String content = extras.getString("content");
        String imgUrl = extras.getString("imgUrl");
        String id = extras.getString("id");
        edtGroupName.setText(groupName);
        edtTitle.setText(title);
        edtContent.setText(content);
        edtImgUrl.setText(imgUrl);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostModel u = new PostModel();
                u.setGroupName(edtGroupName.getText().toString());
                u.setTitle(edtTitle.getText().toString());
                u.setContent(edtContent.getText().toString());
                u.setImageUrl(edtImgUrl.getText().toString());
                if(id != null && id.trim().length() > 0){
                    updatePost(id, u);
                    Intent intent = new Intent(PostActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    addPost(u);
                    Intent intent = new Intent(PostActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePost(id);
                Intent intent = new Intent(PostActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void addPost(PostModel u){
        Call<ResponseParser> call = userService.addPost(u);
        call.enqueue(new Callback<ResponseParser>() {
            @Override
            public void onResponse(Call<ResponseParser> call, Response<ResponseParser> response) {
                if(response.isSuccessful()){
                    Toast.makeText(PostActivity.this, "Created successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseParser> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void updatePost(String id, PostModel u){
        Call<ResponseParser> call = userService.updatePost(id, u);
        call.enqueue(new Callback<ResponseParser>() {
            @Override
            public void onResponse(Call<ResponseParser> call, Response<ResponseParser> response) {
                if(response.isSuccessful()){
                    Toast.makeText(PostActivity.this, "Updated successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseParser> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void deletePost(String id){
        Call<ResponseParser> call = userService.deletePost(id);
        call.enqueue(new Callback<ResponseParser>() {
            @Override
            public void onResponse(Call<ResponseParser> call, Response<ResponseParser> response) {
                if(response.isSuccessful()){
                    Toast.makeText(PostActivity.this, "Deleted successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseParser> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}