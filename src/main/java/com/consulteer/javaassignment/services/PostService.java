package com.consulteer.javaassignment.services;

import com.consulteer.javaassignment.dto.PostDTO;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostDTO postDTO);

    PostDTO updatePost(PostDTO postDTO, Long postId);

    PostDTO getPostById(Long postId);

    List<PostDTO> getAllPosts();

    void deletePost(Long postId);

    PostDTO updateLikes(Long postId);

    PostDTO updateDislikes(Long postId);

}
