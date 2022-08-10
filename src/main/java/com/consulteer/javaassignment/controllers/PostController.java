package com.consulteer.javaassignment.controllers;

import com.consulteer.javaassignment.dto.PostDTO;
import com.consulteer.javaassignment.models.Post;
import com.consulteer.javaassignment.payloads.ApiResponse;
import com.consulteer.javaassignment.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/")
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody Post post) {

        PostDTO createPostDTO = this.postService.createPost(post);

        return new ResponseEntity<>(createPostDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody Post post, @PathVariable("postId") Long postId) {

        PostDTO updatedPost = this.postService.updatePost(post, postId);

        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") Long postId) {

        this.postService.deletePost(postId);

        return new ResponseEntity(new ApiResponse("Post deleted successfully", true), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<PostDTO>> getAllPosts() {

        return ResponseEntity.ok(this.postService.getAllPosts());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO>  getSinglePost(@PathVariable Long postId) {
        return ResponseEntity.ok(this.postService.getPostById(postId));
    }

    @PutMapping("/{postId}/like")
    public ResponseEntity<PostDTO> updateLikesOnPost(@PathVariable("postId") Long postId){
        return ResponseEntity.ok(this.postService.updateLikes(postId));
    }

    @PutMapping("/{postId}/dislike")
    public ResponseEntity<PostDTO> updateDislikesOnPost(@PathVariable("postId") Long postId){
        return ResponseEntity.ok(this.postService.updateDislikes(postId));
    }
}
