package com.consulteer.javaassignment.mapper;

import com.consulteer.javaassignment.dto.PostDto;
import com.consulteer.javaassignment.models.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper{

    public PostMapper() {
    }

    public PostDto convert(Post post) {
        return new PostDto(post.getTitle(),
                post.getBody(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getLikes(),
                post.getDislikes());
    }

    public Post convert(PostDto postDto) {
        return new Post(postDto.getTitle(),
                postDto.getBody(),
                postDto.getCreatedAt(),
                postDto.getUpdatedAt(),
                postDto.getLikes(),
                postDto.getDislikes());
    }

    public Post convertCreatedPost(PostDto postDto) {
        return new Post(postDto.getTitle(),
                postDto.getBody(),
                postDto.getCreatedAt(),
                postDto.getUpdatedAt(),
                0,
                0);
    }
}
