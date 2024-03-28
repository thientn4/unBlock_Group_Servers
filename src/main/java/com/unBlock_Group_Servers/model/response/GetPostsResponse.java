package com.unBlock_Group_Servers.model.response;
import com.unBlock_Group_Servers.model.object.Post;

import java.util.List;
public class GetPostsResponse {

    public GetPostsResponse(String status, List<String> tags, List<Post> posts) {
        this.status = status;
        this.tags = tags;
        this.posts = posts;
    }

    public GetPostsResponse(String status) {
        this.status = status;
        this.tags = null;
        this.posts = null;;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    private String status;
    private List<String>tags;
    private List<Post>posts;

}
