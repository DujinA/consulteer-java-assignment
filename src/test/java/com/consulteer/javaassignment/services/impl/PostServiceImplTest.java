package com.consulteer.javaassignment.services.impl;

import com.consulteer.javaassignment.exceptions.BadRequestException;
import com.consulteer.javaassignment.models.Post;
import com.consulteer.javaassignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    @Mock
    private ModelMapper modelMapper;
    @Mock
    private PostRepository postRepository;

    @Mock
    Post post;

    @InjectMocks
    private PostServiceImpl underTest;

    @BeforeEach
    void setUp() {

        underTest = new PostServiceImpl(postRepository, modelMapper);
    }

    @Test
    void canCreatePost() {

        Post post = new Post(
                1L,
                "Title",
                "Content",
                "default.png",
                null,
                null,
                0,
                0,
                null
        );

        underTest.createPost(post);

        ArgumentCaptor<Post> postArgumentCaptor = ArgumentCaptor.forClass(Post.class);

        verify(postRepository).save(postArgumentCaptor.capture());

        Post createPost = postArgumentCaptor.getValue();

        assertThat(createPost).isEqualTo(post);
    }

    @Test
    void willThrowIfPostIdIsTaken() {

        Post post = new Post(
                1L,
                "Title",
                "Content",
                "default.png",
                null,
                null,
                0,
                0,
                null
        );

        given(postRepository.selectedPostExists(post.getId()))
                .willReturn(true);

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