package com.unBlock_Group_Servers.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController //--------------> Marks a class as a controller that handles REST API requests
public class AddPostController {
    @PostMapping("/add/post") //--------------> Maps a method in a Spring controller to handle HTTP GET requests made to a path
    public ResponseEntity<String> home(
            @RequestHeader(name = "email",required = true) String email
    ){
        /*
         * TEST URL:
         * http://localhost:8080
         * */
        System.out.println(email);
        return ResponseEntity.status(200).body("success");
    }
}
