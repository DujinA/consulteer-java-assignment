package com.consulteer.javaassignment.services;

import com.consulteer.javaassignment.models.Comment;

import java.util.Collection;

public interface CommentService {

    Comment createComment(Comment comment, Long postId);

    Comment updateComment(Comment comment, Long commentId);

    Comment getCommentById(Long CommentId);

    Collection<Comment> getAllComments();

    void deleteComment(Long commentId);
}
