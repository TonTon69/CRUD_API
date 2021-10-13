package com.example.crud_api;

import com.example.crud_api.Model.PostModel;

import java.util.List;

public class ResponseParser {
    private String message;
    private List<PostModel> data;

    public ResponseParser() {
    }

    public ResponseParser(String message, List<PostModel> data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<PostModel> getData() {
        return data;
    }

    public void setData(List<PostModel> data) {
        this.data = data;
    }
}
