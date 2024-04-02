package com.unBlock_Group_Servers.controller;

import com.unBlock_Group_Servers.model.Query;
import com.unBlock_Group_Servers.model.response.GetPostsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //--------------> Marks a class as a controller that handles REST API requests
public class GetRepliesController {

    @Autowired //--------------------> automates dependency (bean) injection
    private Query query;
    @GetMapping("/get/replies") //--------------> Maps a method in a Spring controller to handle HTTP GET requests made to a path
    public ResponseEntity<GetPostsResponse> home(
            @RequestParam(name = "postId",required = true) String postId
    ){
        try {
            return ResponseEntity.status(200).body(new GetPostsResponse(
                    "success",
                    this.query.getPostTags(postId),
                    this.query.getReplies(postId)
            ));
        }catch(Exception e){
            System.out.println(e.toString());
            return ResponseEntity.status(200).body(new GetPostsResponse("failed"));
        }
    }
}
