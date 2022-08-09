package com.consulteer.javaassignment.repositories;

import com.consulteer.javaassignment.models.Comment;
import com.consulteer.javaassignment.models.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.HashSet;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PostRepositoryTest {

    @Autowired
    private PostRepository underTest;

    @Test
    void  itShouldCheckIfPostAlreadyExists() {

        //given
        Post post = new Post(
                "Title",
                "Content",
                "default.png",
                null,
                null,
                0,
                0,
                null
        );

        underTest.save(post);
        //when
        Boolean expected = underTest.selectedPostExists(post.getId());
        //then
        assertThat(expected).isTrue();
    }

    @Test
    void  itShouldCheckIfPostDoesNotExist() {

        //given
        Long id = 1L;
        //when
        Boolean expected = underTest.selectedPostExists(id);
        //then
        assertThat(expected).isFalse();
    }
}