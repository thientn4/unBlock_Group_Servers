package com.unBlock_Group_Servers.controller;

import com.unBlock_Group_Servers.model.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //--------------> Marks a class as a controller that handles REST API requests
public class HighlightPostController {
    @Autowired //--------------------> automates dependency (bean) injection
    private Query query;
    @PostMapping("/highlight/post") //--------------> Maps a method in a Spring controller to handle HTTP GET requests made to a path
    public ResponseEntity<String> home(
            @RequestParam(name = "postId",required = true) int postId,
            @RequestParam(name = "highlight",required = true) boolean highlight
    ) {
        query.highlightPost(postId,highlight);
        return ResponseEntity.status(200).body("success");
    }
}
