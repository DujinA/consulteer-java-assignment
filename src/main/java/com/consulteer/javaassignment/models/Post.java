package com.consulteer.javaassignment.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 10000, nullable = false)
    private String body;

    private Date createdAt;

    private Date updatedAt;

    @Column(nullable = false)
    private Integer likes = 0;

    @Column(nullable = false)
    private Integer dislikes = 0;

    @JsonManagedReference
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

    public Post(String title,
                String body,
                Date createdAt,
                Date updatedAt,
                Integer likes,
                Integer dislikes,
                Set<Comment> comments) {
        this.title = title;
        this.body = body;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.likes = likes;
        this.dislikes = dislikes;
        this.comments = comments;
    }
}
