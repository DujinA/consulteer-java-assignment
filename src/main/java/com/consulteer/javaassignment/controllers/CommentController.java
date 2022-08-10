package com.consulteer.javaassignment.controllers;

import com.consulteer.javaassignment.dto.CommentDTO;
import com.consulteer.javaassignment.models.Comment;
import com.consulteer.javaassignment.payloads.ApiResponse;
import com.consulteer.javaassignment.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@Valid @RequestBody Comment comment, @PathVariable Long postId) {

        CommentDTO createCommentDTO = this.commentService.createComment(comment, postId);
        return new ResponseEntity<CommentDTO>(createCommentDTO, HttpStatus.CREATED);
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@Valid @RequestBody Comment comment, @PathVariable("commentId") Long commentId) {

        CommentDTO updatedComment = this.commentService.updateComment(comment, commentId);

        return ResponseEntity.ok(updatedComment);
    }

    @GetMapping("/comments")
    public ResponseEntity<List<CommentDTO>> getAllComments() {

        return ResponseEntity.ok(this.commentService.getAllComments());
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<CommentDTO> getSingleComment(@PathVariable Long commentId) {
        return ResponseEntity.ok(this.commentService.getCommentById(commentId));
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Long commentId) {

        this.commentService.deleteComment(commentId);

        return new ResponseEntity(new ApiResponse("Comment deleted successfully", true), HttpStatus.OK);
    }
}
