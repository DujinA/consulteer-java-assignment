package com.consulteer.javaassignment.services.impl;

import com.consulteer.javaassignment.dto.CommentDTO;
import com.consulteer.javaassignment.exceptions.ResourceNotFoundException;
import com.consulteer.javaassignment.models.Comment;
import com.consulteer.javaassignment.models.Post;
import com.consulteer.javaassignment.repositories.CommentRepository;
import com.consulteer.javaassignment.repositories.PostRepository;
import com.consulteer.javaassignment.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDTO createComment(Comment comment, Long postId) {

        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

        comment.setPost(post);
        comment.setCreatedAt(new Date());
        Comment savedComment = this.commentRepository.save(comment);

        return this.modelMapper.map(savedComment, CommentDTO.class);
    }

    @Override
    public CommentDTO updateComment(Comment comment, Long commentId) {

        Comment updatedComment = this.commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", " Id ", commentId));

        updatedComment.setUpdatedAt(new Date());
        updatedComment.setContent(comment.getContent());

        Comment savedComment = this.commentRepository.save(updatedComment);

        return this.modelMapper.map(savedComment, CommentDTO.class);
    }

    @Override
    public CommentDTO getCommentById(Long commentId){

        Comment comment = this.commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "post id", commentId));

        return this.modelMapper.map(comment, CommentDTO.class);
    }

    @Override
    public List<CommentDTO> getAllComments() {

        List<Comment> comments = this.commentRepository.findAll();
        List<CommentDTO> commentDTOs = comments.stream()
                .map(comment -> this.modelMapper.map(comment, CommentDTO.class)).collect(Collectors.toList());

        return commentDTOs;
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = this.commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", " Id", commentId));

        this.commentRepository.delete(comment);
    }
}
