package com.consulteer.javaassignment.repositories;

import com.consulteer.javaassignment.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("" +
            "SELECT CASE WHEN COUNT(p) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Post p " +
            "WHERE p.id = ?1")
    Boolean selectedPostExists(Long id);
}
