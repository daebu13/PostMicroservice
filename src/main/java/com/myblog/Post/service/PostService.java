package com.myblog.Post.service;

import com.myblog.Post.payload.PostDto;

public interface PostService {
    PostDto createPost(PostDto dto);

    PostDto getPostById(String postId);

    PostDto getPostWithComments(String postId);
}
