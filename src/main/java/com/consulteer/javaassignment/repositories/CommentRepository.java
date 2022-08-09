package com.consulteer.javaassignment.repositories;

import com.consulteer.javaassignment.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
