package com.consulteer.javaassignment.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(length = 10000, nullable = false)
    private String content;

    private Date createdAt;

    private Date updatedAt;

    @JsonBackReference
    @ManyToOne
    private Post post;

    public Comment(String content, Date createdAt, Date updatedAt, Post post) {
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.post = post;
    }
}
