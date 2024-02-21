package com.myblog.Post.service.serviceImpl;

import com.myblog.Post.config.RestTemplateConfig;
import com.myblog.Post.entity.Post;
import com.myblog.Post.payload.PostDto;
import com.myblog.Post.repository.PostRepository;
import com.myblog.Post.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestTemplateConfig restTemplate;

    @Override
    public PostDto createPost(PostDto dto) {
        String postId = UUID.randomUUID().toString();
        Post post = new Post();
        post.setId(postId);
        post.setTitle(dto.getTitle());
        post.setDescription(dto.getDescription());
        post.setContent(dto.getContent());
        postRepository.save(post);

        PostDto postDto = new PostDto();

        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());


        return postDto;
    }

    @Override
    public PostDto getPostById(String postId) {
        Post post = postRepository.findById(postId).get();
        PostDto dto =new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        return dto;
    }

    @Override
    public PostDto getPostWithComments(String postId) {
        Post post = postRepository.findById(postId).get();
        ArrayList comments = restTemplate.getRestTemplate().getForObject("http://COMMENT-SERVICE/api/comments/" + postId, ArrayList.class);
        PostDto dto = mapToDto(post);
        dto.setId(post.getId());
        dto.setComments(comments);
        return dto;
    }

    Post mapToEntity(PostDto dto){
        Post post = modelMapper.map(dto, Post.class);
        return post;
    }
    PostDto mapToDto(Post post){
        PostDto dto = modelMapper.map(post, PostDto.class);
        return dto;
    }
}
