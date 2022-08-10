package com.consulteer.javaassignment.services;

import com.consulteer.javaassignment.dto.CommentDTO;
import com.consulteer.javaassignment.models.Comment;

import java.util.List;

public interface CommentService {

    CommentDTO createComment(Comment comment, Long postId);

    CommentDTO updateComment(Comment comment, Long commentId);

    CommentDTO getCommentById(Long CommentId);

    List<CommentDTO> getAllComments();

    void deleteComment(Long commentId);
}
