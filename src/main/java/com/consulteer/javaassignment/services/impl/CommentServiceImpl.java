package com.consulteer.javaassignment.services.impl;

import com.consulteer.javaassignment.exceptions.ResourceNotFoundException;
import com.consulteer.javaassignment.models.Comment;
import com.consulteer.javaassignment.models.Post;
import com.consulteer.javaassignment.repositories.CommentRepository;
import com.consulteer.javaassignment.repositories.PostRepository;
import com.consulteer.javaassignment.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Comment createComment(Comment comment, Long postId) {

        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));

        comment.setPost(post);
        comment.setCreatedAt(new Date());
        comment.setUpdatedAt(new Date());
        return this.commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Comment comment, Long commentId) {

        Comment updatedComment = findCommentById(commentId);

        updatedComment.setContent(comment.getContent());
        updatedComment.setUpdatedAt(new Date());
        return this.commentRepository.save(updatedComment);

    }

    @Override
    public Collection<Comment> getAllComments() {

        return commentRepository.findAll().stream().toList();
    }

    @Override
    public Comment getCommentById(Long commentId){

        return commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + commentId));
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = findCommentById(commentId);

        this.commentRepository.deleteById(comment.getId());
    }

    private Comment findCommentById(Long commentId) {
        return this.commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id: " + commentId));
    }
}
