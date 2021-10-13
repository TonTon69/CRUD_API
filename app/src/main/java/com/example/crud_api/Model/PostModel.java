package com.example.crud_api.Model;

public class PostModel {
    private String _id;
    private String title;
    private String content;
    private String imageUrl;
    private String groupName;

    public PostModel() {
    }

    public PostModel(String _id, String title, String content, String imageUrl, String groupName) {
        this._id = _id;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.groupName = groupName;
    }

    public String getPostId() {
        return _id;
    }

    public void setPostId(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
