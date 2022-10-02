package com.consulteer.javaassignment.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class PostDto {
    private final String title;
    private final String body;
    private final Date createdAt;
    private final Date updatedAt;
    private final Integer likes;
    private final Integer dislikes;

    public PostDto(String title,
                   String body,
                   Date createdAt,
                   Date updatedAt,
                   Integer likes,
                   Integer dislikes) {
        this.title = title;
        this.body = body;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.likes = likes;
        this.dislikes = dislikes;
    }
}
