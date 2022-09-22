package com.consulteer.javaassignment.services.impl;

import com.consulteer.javaassignment.exceptions.BadRequestException;
import com.consulteer.javaassignment.exceptions.ResourceNotFoundException;
import com.consulteer.javaassignment.models.Post;
import com.consulteer.javaassignment.repositories.PostRepository;
import com.consulteer.javaassignment.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post createPost(Post post){

        boolean postExists = postRepository.doesSelectedPostExist(post.getId());

        if (postExists) {
            throw new BadRequestException("Id " + post.getId() + " already taken");
        }

        post.setCreatedAt(new Date());
        post.setUpdatedAt(new Date());

        return this.postRepository.save(post);
    }

    @Override
    public Post updatePost(Post post, Long postId){

        Post updatedPost = findPostById(postId);

        updatedPost.setTitle(post.getTitle());
        updatedPost.setBody(post.getBody());
        updatedPost.setLikes(post.getLikes());
        updatedPost.setDislikes(post.getDislikes());
        updatedPost.setUpdatedAt(new Date());

        return this.postRepository.save(updatedPost);

    }

    @Override
    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(Long postId){
        return postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));
    }

    @Override
    public void deletePost(Long postId){

        Post post = findPostById(postId);

        this.postRepository.deleteById(post.getId());
    }

    private Post findPostById(Long postId) {
        return this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));
    }

    @Override
    public Post updateLikes(Long postId) {
        return null;
    }

    @Override
    public Post updateDislikes(Long postId) {
        return null;
    }
}
