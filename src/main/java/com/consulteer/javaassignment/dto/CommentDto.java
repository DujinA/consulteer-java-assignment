package com.consulteer.javaassignment.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class CommentDto {
    private final String content;
    private final Date createdAt;
    private final Date updatedAt;

    public CommentDto(String content, Date createdAt, Date updatedAt) {
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
