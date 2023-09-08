package com.ms.controller;

import com.ms.dto.Post;
import com.ms.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @Operation(
            summary = "Retrieve all posts",
            description = "Get all posts and monitor logs to see no of queries it sends to db")
    @GetMapping("/posts")
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }

    @Operation(
            summary = "Retrieve post by id e.g. 1,2,3",
            description = "Get post by id, monitor logs to see number of queries sent to db.")
    @GetMapping("/posts/{id}")
    public Post getById(@PathVariable Long id){
        return postService.getById(id);
    }

    @Operation(
            summary = "Retrieve post by user id e.g. 1,2,3",
            description = "Get post by user id, monitor logs to see number of queries sent to db.")
    @GetMapping("/posts/users/{id}")
    public List<Post> getByUserId(@PathVariable Long id){
        return postService.findAllByUserId(id);
    }
}
