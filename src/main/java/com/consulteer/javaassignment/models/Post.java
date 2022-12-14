package com.consulteer.javaassignment.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_title", length = 100, nullable = false)
    private String title;

    @Column(length = 10000, nullable = false)
    private String content;

    private String imageName = "default.png";

    private Date createdAt;

    private Date updatedAt;

    private Integer likes = 0;

    private Integer dislikes = 0;

    @JsonManagedReference
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

    public Post(String title, String content, String imageName, Date createdAt, Date updatedAt, Integer likes, Integer dislikes, Set<Comment> comments) {
        this.title = title;
        this.content = content;
        this.imageName = imageName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.likes = likes;
        this.dislikes = dislikes;
        this.comments = comments;
    }
}
