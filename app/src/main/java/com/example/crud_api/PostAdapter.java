package com.example.crud_api;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.example.crud_api.Model.PostModel;
import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostAdapter extends ArrayAdapter<PostModel> {
    private Context context;
    private List<PostModel> users;

    public PostAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<PostModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.users = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_post, parent, false);

        TextView txtGroupNameList = (TextView) rowView.findViewById(R.id.txtGroupNameList);
        TextView txtTitleList = (TextView) rowView.findViewById(R.id.txtTitleList);
        TextView txtContentList = (TextView) rowView.findViewById(R.id.txtContentList);
        ImageView imgList = (ImageView) rowView.findViewById(R.id.imgList);

        txtGroupNameList.setText(String.format("GROUP NAME: %s", users.get(pos).getGroupName()));
        txtTitleList.setText(String.format("TITLE: %s", users.get(pos).getTitle()));
        txtContentList.setText(String.format("CONTENT: %s", users.get(pos).getContent()));
        Picasso.with(context).load(users.get(pos).getImageUrl()).into(imgList);


        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start Activity User Form
                Intent intent = new Intent(context, PostActivity.class);
                intent.putExtra("id", users.get(pos).getPostId());
                intent.putExtra("groupName", users.get(pos).getGroupName());
                intent.putExtra("title", users.get(pos).getTitle());
                intent.putExtra("content", String.valueOf(users.get(pos).getContent()));
                intent.putExtra("imgUrl", users.get(pos).getImageUrl());
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
