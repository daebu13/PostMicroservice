package com.myblog.Post.controller;

import com.myblog.Post.payload.PostDto;
import com.myblog.Post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto dto){
      PostDto postDto  =postService.createPost(dto);
      return new ResponseEntity<>(postDto, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable String postId){
        PostDto postById = postService.getPostById(postId);
        return new ResponseEntity<>(postById,HttpStatus.OK);

    }
    @GetMapping("/postWithComments/{postId}")
    public  ResponseEntity<PostDto> getPostWithComments(@PathVariable String postId){
        PostDto dto=postService.getPostWithComments(postId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }


}
