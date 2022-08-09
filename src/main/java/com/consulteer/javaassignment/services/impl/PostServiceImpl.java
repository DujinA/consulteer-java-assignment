package com.consulteer.javaassignment.services.impl;

import com.consulteer.javaassignment.dto.PostDTO;
import com.consulteer.javaassignment.exceptions.BadRequestException;
import com.consulteer.javaassignment.exceptions.ResourceNotFoundException;
import com.consulteer.javaassignment.models.Post;
import com.consulteer.javaassignment.repositories.PostRepository;
import com.consulteer.javaassignment.services.PostService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDTO createPost(PostDTO postDTO){

        Post post = this.modelMapper.map(postDTO, Post.class);

        Boolean postExists = this.postRepository.selectedPostExists(post.getId());

        if (postExists) {
            throw new BadRequestException("Id " + post.getId() + " already taken");
        }

        post.setImageName("default.png");
        post.setLikes(0);
        post.setDislikes(0);
        post.setCreatedAt(new Date());

        Post savedPost = this.postRepository.save(post);

        return this.modelMapper.map(savedPost, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Long postId){

        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", " Id ", postId));

        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setImageName(postDTO.getImageName());
        post.setLikes(postDTO.getLikes());
        post.setDislikes(postDTO.getLikes());
        post.setUpdatedAt(new Date());

        Post updatedPost = this.postRepository.save(post);

        return this.modelMapper.map(updatedPost, PostDTO.class);
    }

    @Override
    public PostDTO getPostById(Long postId){

        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
        return this.modelMapper.map(post, PostDTO.class);
    }

    @Override
    public List<PostDTO> getAllPosts(){

        List<Post> posts = this.postRepository.findAll();
        List<PostDTO> postDTOs = posts.stream()
                .map(post -> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());

        return postDTOs;
    }

    @Override
    public void deletePost(Long postId){

        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", " Id", postId));

        this.postRepository.delete(post);
    }

    @Override
    public PostDTO updateLikes(Long postId) {
        return null;
    }

    @Override
    public PostDTO updateDislikes(Long postId) {
        return null;
    }
}
