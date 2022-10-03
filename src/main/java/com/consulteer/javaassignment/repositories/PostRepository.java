package com.consulteer.javaassignment.repositories;

import com.consulteer.javaassignment.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("" +
            "SELECT CASE WHEN COUNT(p) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Post p " +
            "WHERE p.id = ?1")
    Boolean doesSelectedPostExist(Long id);

    Optional<Post> findById(Long id);
}
