package com.consulteer.javaassignment.services;

import com.consulteer.javaassignment.models.Post;

import java.util.List;

public interface PostService {

    Post createPost(Post post);

    Post updatePost(Post post, Long postId);

    List<Post> getAllPosts();

    Post getPostById(Long postId);

    void deletePost(Long postId);

    Post updateLikes(Long postId);

    Post updateDislikes(Long postId);

}
