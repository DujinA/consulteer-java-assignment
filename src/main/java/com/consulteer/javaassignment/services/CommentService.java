package com.consulteer.javaassignment.services;

import com.consulteer.javaassignment.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    CommentDTO createComment(CommentDTO commentDTO, Long postId);

    CommentDTO updateComment(CommentDTO commentDTO, Long commentId);

    CommentDTO getCommentById(Long CommentId);

    List<CommentDTO> getAllComments();

    void deleteComment(Long commentId);
}
