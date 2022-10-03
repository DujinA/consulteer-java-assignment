package com.consulteer.javaassignment.services.impl;

import com.consulteer.javaassignment.dto.PostDto;
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
    @Mock
    private Post post = new Post(1L, "title", "body", null, null, 0, 0, null);
    @Mock
    private PostDto postDto = new PostDto("title", "body", null, null, 0, 0);
    @InjectMocks
    private PostServiceImpl underTest;

    @BeforeEach
    void setUp() {

        underTest = new PostServiceImpl(postRepository, postMapper);
    }

    @Test
    void canCreatePost() {
        // given
        when(postMapper.convertCreatedPost(postDto)).thenReturn(post);
        when(postMapper.convert(post)).thenReturn(postDto);

        // when
        underTest.createPost(postDto);

        // then
        ArgumentCaptor<Post> postArgumentCaptor = ArgumentCaptor.forClass(Post.class);

        verify(postRepository).save(postArgumentCaptor.capture());

        Post createPost = postArgumentCaptor.getValue();

        assertThat(createPost).isEqualTo(post);
    }

    @Test
    void willThrowIfPostIdIsTaken() {
        // given
        when(postMapper.convertCreatedPost(postDto)).thenReturn(post);
        when(postMapper.convert(post)).thenReturn(postDto);

        PostDto postDto = postMapper.convert(post);

        given(postRepository.doesSelectedPostExist(post.getId()))
                .willReturn(true);

        // when-then
        assertThatThrownBy(() -> underTest.createPost(postDto))
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