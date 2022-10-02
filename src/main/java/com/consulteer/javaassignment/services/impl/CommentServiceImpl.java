package com.consulteer.javaassignment.services.impl;

import com.consulteer.javaassignment.dto.CommentDto;
import com.consulteer.javaassignment.exceptions.ResourceNotFoundException;
import com.consulteer.javaassignment.mapper.CommentMapper;
import com.consulteer.javaassignment.models.Comment;
import com.consulteer.javaassignment.models.Post;
import com.consulteer.javaassignment.repositories.CommentRepository;
import com.consulteer.javaassignment.repositories.PostRepository;
import com.consulteer.javaassignment.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    @Autowired
    private final CommentRepository commentRepository;
    @Autowired
    private final PostRepository postRepository;
    @Autowired
    private final CommentMapper commentMapper;

    @Override
    public CommentDto createComment(Comment comment, Long postId) {
        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));

        comment.setPost(post);
        commentRepository.save(comment);

        return commentMapper.convert(comment);
    }

    @Override
    public CommentDto updateComment(Comment comment, Long commentId) {
        Comment updatedComment = findCommentById(commentId);

        updateBasicFields(comment, updatedComment);
        commentRepository.save(updatedComment);

        return commentMapper.convert(updatedComment);
    }

    private static void updateBasicFields(Comment comment, Comment updatedComment) {
        updatedComment.setContent(comment.getContent());
        updatedComment.setUpdatedAt(comment.getUpdatedAt());
    }

    @Override
    public List<CommentDto> getAllComments() {
        return commentRepository.findAll().stream()
                .map(commentMapper::convert)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long commentId){
        Comment comment = findCommentById(commentId);

        return commentMapper.convert(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = findCommentById(commentId);

        commentRepository.deleteById(comment.getId());
    }

    private Comment findCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id: " + commentId));
    }
}
