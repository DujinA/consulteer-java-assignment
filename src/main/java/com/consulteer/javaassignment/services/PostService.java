package com.consulteer.javaassignment.services;

import com.consulteer.javaassignment.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostDto updatePost(PostDto postDto, Long postId);

    List<PostDto> getAllPosts();

    PostDto getPostById(Long postId);

    void deletePost(Long postId);

    PostDto updateLikes(Long postId);

    PostDto updateDislikes(Long postId);
}
