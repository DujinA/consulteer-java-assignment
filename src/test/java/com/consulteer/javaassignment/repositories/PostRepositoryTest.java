package com.consulteer.javaassignment.repositories;

import com.consulteer.javaassignment.models.Post;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class PostRepositoryTest {

    @Autowired
    private PostRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckIfPostAlreadyExists() {

        Long id = 1L;

        // given
        Post post = new Post(
                id,
                "Title",
                "Content",
                null,
                null,
                0,
                0,
                null
        );

        underTest.save(post);

        // when
        Boolean expected = underTest.doesSelectedPostExist(id);

        // then
        assertThat(expected).isTrue();
    }

    @Test
    void  itShouldCheckIfPostDoesNotExist() {

        // given
        Long id = 1L;

        // when
        Boolean expected = underTest.doesSelectedPostExist(id);

        // then
        assertThat(expected).isFalse();
    }
}