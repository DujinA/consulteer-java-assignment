package com.consulteer.javaassignment.controllers;

import com.consulteer.javaassignment.dto.PostDto;
import com.consulteer.javaassignment.models.Post;
import com.consulteer.javaassignment.payloads.ApiResponse;
import com.consulteer.javaassignment.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    @Autowired
    private final PostService postService;

    @PostMapping("/")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody Post post) {
        return new ResponseEntity<>(postService.createPost(post), HttpStatus.CREATED);
    }

    @PutMapping("/{post-id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody Post post, @PathVariable("post-id") Long postId) {
        return ResponseEntity.ok(postService.updatePost(post, postId));
    }

    @GetMapping("/")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{post-id}")
    public ResponseEntity<PostDto>  getPostById(@PathVariable("post-id") Long postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @DeleteMapping("/{post-id}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable("post-id") Long postId) {
        postService.deletePost(postId);

        return new ResponseEntity<>(new ApiResponse("Post deleted successfully", true), HttpStatus.OK);
    }

    @PutMapping("/{post-id}/like")
    public ResponseEntity<PostDto> updateLikesOnPost(@PathVariable("post-id") Long postId){
        return ResponseEntity.ok(postService.updateLikes(postId));
    }

    @PutMapping("/{post-id}/dislike")
    public ResponseEntity<PostDto> updateDislikesOnPost(@PathVariable("post-id") Long postId){
        return ResponseEntity.ok(postService.updateDislikes(postId));
    }
}
