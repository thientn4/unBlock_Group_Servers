package com.unBlock_Group_Servers.controller;

import com.unBlock_Group_Servers.model.Query;
import com.unBlock_Group_Servers.model.request.AddPostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //--------------> Marks a class as a controller that handles REST API requests
public class DeletePostController {
    @Autowired //--------------------> automates dependency (bean) injection
    private Query query;
    @PostMapping("/delete/post") //--------------> Maps a method in a Spring controller to handle HTTP GET requests made to a path
    public ResponseEntity<String> home(
            @RequestHeader(name = "email",required = true) String email,
            @RequestParam(name = "postId",required = true) int postId
    ) {
        return ResponseEntity.status(200).body((query.deletePost(postId,email)>0)?"success":"failed");
    }
}
