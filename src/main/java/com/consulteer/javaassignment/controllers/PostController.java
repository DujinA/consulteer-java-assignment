package com.consulteer.javaassignment.controllers;

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
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post) {

        Post createPostDTO = this.postService.createPost(post);

        return new ResponseEntity<>(createPostDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(@Valid @RequestBody Post post, @PathVariable("postId") Long postId) {

        Post updatedPost = this.postService.updatePost(post, postId);

        return ResponseEntity.ok(updatedPost);
    }

    @GetMapping("/")
    public ResponseEntity<List<Post>> getAllPosts() {

        return ResponseEntity.ok(this.postService.getAllPosts());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post>  getPostById(@PathVariable Long postId) {
        return ResponseEntity.ok(this.postService.getPostById(postId));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") Long postId) {

        this.postService.deletePost(postId);

        return new ResponseEntity<>(new ApiResponse("Post deleted successfully", true), HttpStatus.OK);
    }

    @PutMapping("/{postId}/like")
    public ResponseEntity<Post> updateLikesOnPost(@PathVariable("postId") Long postId){
        return ResponseEntity.ok(this.postService.updateLikes(postId));
    }

    @PutMapping("/{postId}/dislike")
    public ResponseEntity<Post> updateDislikesOnPost(@PathVariable("postId") Long postId){
        return ResponseEntity.ok(this.postService.updateDislikes(postId));
    }
}
