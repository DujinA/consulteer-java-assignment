package com.consulteer.javaassignment.services.impl;

import com.consulteer.javaassignment.exceptions.BadRequestException;
import com.consulteer.javaassignment.mapper.PostMapper;
import com.consulteer.javaassignment.models.Post;
import com.consulteer.javaassignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {
    @Mock
    private PostRepository postRepository;
    @Mock
    private PostMapper postMapper;
    @InjectMocks
    private PostServiceImpl underTest;

    @BeforeEach
    void setUp() {

        underTest = new PostServiceImpl(postRepository, postMapper);
    }

    @Test
    void canCreatePost() {
        // given
        Post post = new Post(
                1L,
                "Title",
                "Content",
                null,
                null,
                0,
                0,
                null
        );

        // when
        underTest.createPost(post);

        // then
        ArgumentCaptor<Post> postArgumentCaptor = ArgumentCaptor.forClass(Post.class);

        verify(postRepository).save(postArgumentCaptor.capture());

        Post createPost = postArgumentCaptor.getValue();

        assertThat(createPost).isEqualTo(post);
    }

    @Test
    void willThrowIfPostIdIsTaken() {
        // given
        Post post = new Post(
                1L,
                "Title",
                "Content",
                null,
                null,
                0,
                0,
                null
        );

        given(postRepository.doesSelectedPostExist(post.getId()))
                .willReturn(true);

        // when-then
        assertThatThrownBy(() -> underTest.createPost(post))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Id " + post.getId() + " already taken");

        verify(postRepository, never()).save(any());
    }

    @Test
    void canGetAllPosts() {
        underTest.getAllPosts();

        verify(postRepository).findAll();
    }
}