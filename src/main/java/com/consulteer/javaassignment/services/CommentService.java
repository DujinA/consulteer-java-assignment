package com.consulteer.javaassignment.services;

import com.consulteer.javaassignment.dto.CommentDto;
import com.consulteer.javaassignment.models.Comment;

import java.util.List;

public interface CommentService {
    CommentDto createComment(Comment comment, Long postId);

    CommentDto updateComment(Comment comment, Long commentId);

    CommentDto getCommentById(Long CommentId);

    List<CommentDto> getAllComments();

    void deleteComment(Long commentId);
}
