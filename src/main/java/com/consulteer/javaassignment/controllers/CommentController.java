package com.consulteer.javaassignment.controllers;

import com.consulteer.javaassignment.dto.CommentDto;
import com.consulteer.javaassignment.models.Comment;
import com.consulteer.javaassignment.payloads.ApiResponse;
import com.consulteer.javaassignment.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    @Autowired
    private final CommentService commentService;

    @PostMapping("/posts/{post-id}/comments")
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto commentDto, @PathVariable("post-id") Long postId) {
        return new ResponseEntity<>(commentService.createComment(commentDto, postId), HttpStatus.CREATED);
    }

    @PutMapping("/comments/{comment-id}")
    public ResponseEntity<CommentDto> updateComment(@Valid @RequestBody CommentDto commentDto, @PathVariable("comment-id") Long commentId) {
        return ResponseEntity.ok(commentService.updateComment(commentDto, commentId));
    }

    @GetMapping("/comments")
    public ResponseEntity<List<CommentDto>> getAllComments() {
        return ResponseEntity.ok(commentService.getAllComments());
    }

    @GetMapping("/comments/{comment-id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("comment-id") Long commentId) {
        return ResponseEntity.ok(commentService.getCommentById(commentId));
    }

    @DeleteMapping("/comments/{comment-id}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable("comment-id") Long commentId) {
        commentService.deleteComment(commentId);

        return new ResponseEntity<>(new ApiResponse("Comment deleted successfully", true), HttpStatus.OK);
    }
}
