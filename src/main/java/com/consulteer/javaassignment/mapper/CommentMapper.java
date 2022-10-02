package com.consulteer.javaassignment.mapper;

import com.consulteer.javaassignment.dto.CommentDto;
import com.consulteer.javaassignment.models.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentMapper() {
    }

    public CommentDto convert(Comment comment) {
        return new CommentDto(comment.getContent(), comment.getCreatedAt(), comment.getUpdatedAt());
    }

    public Comment convert(CommentDto commentDto) {
        return new Comment(commentDto.getContent(), commentDto.getCreatedAt(), commentDto.getUpdatedAt());
    }
}
