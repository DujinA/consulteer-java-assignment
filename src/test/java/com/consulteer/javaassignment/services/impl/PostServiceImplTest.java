package com.consulteer.javaassignment.services.impl;

import com.consulteer.javaassignment.dto.PostDTO;
import com.consulteer.javaassignment.models.Post;
import com.consulteer.javaassignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    @Mock
    private ModelMapper modelMapper;
    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostServiceImpl underTest;

    @BeforeEach
    void setUp() {

        underTest = new PostServiceImpl(postRepository, modelMapper);
    }

    @Test
    @Disabled
    void shouldCreatePost() {
    }

    @Test
    @Disabled
    void canUpdatePost() {
    }

    @Test
    @Disabled
    void canGetPostById() {

    }

    @Test
    void canGetAllPosts() {

        underTest.getAllPosts();

        verify(postRepository).findAll();
    }

    @Test
    @Disabled
    void shouldDeletePost() {
    }
}