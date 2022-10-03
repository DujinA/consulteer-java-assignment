package com.consulteer.javaassignment.services.impl;

import com.consulteer.javaassignment.dto.PostDto;
import com.consulteer.javaassignment.exceptions.BadRequestException;
import com.consulteer.javaassignment.exceptions.ResourceNotFoundException;
import com.consulteer.javaassignment.mapper.PostMapper;
import com.consulteer.javaassignment.models.Post;
import com.consulteer.javaassignment.repositories.PostRepository;
import com.consulteer.javaassignment.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    @Autowired
    private final PostRepository postRepository;
    @Autowired
    private final PostMapper postMapper;

    @Override
    public PostDto createPost(PostDto postDto){
        Post post = postMapper.convertCreatedPost(postDto);
        boolean postExists = postRepository.doesSelectedPostExist(post.getId());

        if (postExists) {
            throw new BadRequestException("Id " + post.getId() + " already taken");
        }
        postRepository.save(post);

        return postMapper.convert(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId){

        Post updatedPost = findPostById(postId);

        updateBasicFields(postDto, updatedPost);
        postRepository.save(updatedPost);

        return postMapper.convert(updatedPost);
    }

    private static void updateBasicFields(PostDto postDto, Post updatedPost) {
        updatedPost.setTitle(postDto.getTitle());
        updatedPost.setBody(postDto.getBody());
    }

    @Override
    public List<PostDto> getAllPosts(){
        return postRepository.findAll().stream()
                .map(postMapper::convert)
                .collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Long postId){
        Post post =  findPostById(postId);

        return postMapper.convert(post);
    }

    @Override
    public void deletePost(Long postId){
        Post post = findPostById(postId);
        postRepository.delete(post);
    }

    private Post findPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));
    }

    @Override
    public PostDto updateLikes(Long postId) {
        Post post = findPostById(postId);

        post.setLikes(post.getLikes() + 1);
        postRepository.save(post);

        return postMapper.convert(post);
    }

    @Override
    public PostDto updateDislikes(Long postId) {
        Post post = findPostById(postId);

        post.setDislikes(post.getDislikes() + 1);
        postRepository.save(post);

        return postMapper.convert(post);
    }
}
