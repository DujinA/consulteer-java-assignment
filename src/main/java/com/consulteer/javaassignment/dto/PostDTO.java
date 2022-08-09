package com.consulteer.javaassignment.dto;

import com.consulteer.javaassignment.models.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostDTO {

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    private String imageName = "default.png";

    private Date createdAt;

    private Date updatedAt;

    private Integer likes = 0;

    private Integer dislikes = 0;

    private Set<CommentDTO> comments = new HashSet<>();
}
