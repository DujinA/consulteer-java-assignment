package com.consulteer.javaassignment.services;

import com.consulteer.javaassignment.dto.PostDto;
import com.consulteer.javaassignment.models.Post;

import java.util.List;

public interface PostService {
    PostDto createPost(Post post);

    PostDto updatePost(Post post, Long postId);

    List<PostDto> getAllPosts();

    PostDto getPostById(Long postId);

    void deletePost(Long postId);

    PostDto updateLikes(Long postId);

    PostDto updateDislikes(Long postId);
}
