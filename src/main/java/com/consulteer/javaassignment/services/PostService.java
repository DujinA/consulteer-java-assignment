package com.consulteer.javaassignment.services;

import com.consulteer.javaassignment.dto.PostDTO;
import com.consulteer.javaassignment.models.Post;

import java.util.List;

public interface PostService {

    PostDTO createPost(Post post);

    PostDTO updatePost(Post post, Long postId);

    PostDTO getPostById(Long postId);

    List<PostDTO> getAllPosts();

    void deletePost(Long postId);

    PostDTO updateLikes(Long postId);

    PostDTO updateDislikes(Long postId);

}
