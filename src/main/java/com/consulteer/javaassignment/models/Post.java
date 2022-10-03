package com.consulteer.javaassignment.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@EntityListeners(AuditingEntityListener.class)
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
    @CreatedDate
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;
    @Column(nullable = false)
    private Integer likes;
    @Column(nullable = false)
    private Integer dislikes;
    @JsonManagedReference
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

    public Post(String title,
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
