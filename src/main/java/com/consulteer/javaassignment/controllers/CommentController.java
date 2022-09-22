package com.consulteer.javaassignment.controllers;

import com.consulteer.javaassignment.models.Comment;
import com.consulteer.javaassignment.payloads.ApiResponse;
import com.consulteer.javaassignment.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class CommentController {

    @Autowired
    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<Comment> createComment(@Valid @RequestBody Comment comment, @PathVariable Long postId) {

        Comment createCommentDTO = this.commentService.createComment(comment, postId);
        return new ResponseEntity<>(createCommentDTO, HttpStatus.CREATED);
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@Valid @RequestBody Comment comment, @PathVariable Long commentId) {

        Comment updatedComment = this.commentService.updateComment(comment, commentId);

        return ResponseEntity.ok(updatedComment);
    }

    @GetMapping("/comments")
    public ResponseEntity<Collection<Comment>> getAllComments() {

        return ResponseEntity.ok(this.commentService.getAllComments());
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long commentId) {
        return ResponseEntity.ok(this.commentService.getCommentById(commentId));
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Long commentId) {

        this.commentService.deleteComment(commentId);

        return new ResponseEntity<>(new ApiResponse("Comment deleted successfully", true), HttpStatus.OK);
    }
}
