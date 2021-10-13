package com.example.crud_api.remote;

public class ApiUtils {

    private ApiUtils(){
    };

    public static final String API_URL = "https://android-article.herokuapp.com/";

    public static UserService getUserService(){
        return RetrofitClient.getClient(API_URL).create(UserService.class);
    }

}
