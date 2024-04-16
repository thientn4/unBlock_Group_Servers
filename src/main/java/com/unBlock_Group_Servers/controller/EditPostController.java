package com.unBlock_Group_Servers.controller;

import com.unBlock_Group_Servers.model.Query;
import com.unBlock_Group_Servers.model.request.AddPostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //--------------> Marks a class as a controller that handles REST API requests
public class EditPostController {

    @Autowired //--------------------> automates dependency (bean) injection
    private Query query;
    @PostMapping("/edit/post") //--------------> Maps a method in a Spring controller to handle HTTP GET requests made to a path
    public ResponseEntity<String> home(
            @RequestHeader(name = "email",required = true) String email,
            @RequestParam(name = "postId",required = true) int postId,
            @RequestBody AddPostRequest body //----------> must use conversion from JSON object to Java object
    ){
        /*
         * TEST URL:
         * http://localhost:8080
         * */
        int affectedRows = query.editPost(
                postId,
                body.getTitle(),
                body.getContent(), // replace temporary links to images from CKEditor with our uploaded links
                body.getPrivate(),
                body.getHighlight(),
                email
        );
        if(affectedRows<=0)return ResponseEntity.status(200).body("failed");
        System.out.println("start add tags "+body.getTags().size());
        for (String tag : body.getTags()) {
            if(query.addPostTag(postId,tag)!=0)System.out.println(tag+" success");
            else System.out.println(tag+" failed");
        }
        return ResponseEntity.status(200).body("success");
    }
}
