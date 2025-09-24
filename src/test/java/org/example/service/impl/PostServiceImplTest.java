package org.example.service.impl;

import org.example.Status;
import org.example.model.Post;
import org.example.model.PostStatus;
import org.example.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    @Mock
    private PostRepository postRepository;

    private PostServiceImpl postService;

    private Post testPost;
    private final Long testPostId = 1L;

    @BeforeEach
    void setUp() {
        postService = new PostServiceImpl(postRepository);

        testPost = new Post();
        testPost.setId(testPostId);
        testPost.setWriterId(123L);
        testPost.setContent("Test post content");
        testPost.setCreated(Instant.now());
        testPost.setUpdated(Instant.now());

        PostStatus status = new PostStatus();
        status.setStatus(Status.UNDER_REVIEW.getValue());
        testPost.setStatus(status);
    }

    @Test
    void createPost_WithValidParameters_ShouldReturnPostId() {
        String writerId = "123";
        String content = "New post content";
        when(postRepository.createPost(any(Post.class))).thenReturn(testPostId);

        Long result = postService.createPost(writerId, content);

        assertNotNull(result);
        assertEquals(testPostId, result);
        verify(postRepository).createPost(any(Post.class));
    }

    @Test
    void createPost_WithInvalidWriterId_ShouldReturnNull() {
        String invalidWriterId = "invalid";
        String content = "New post content";

        Long result = postService.createPost(invalidWriterId, content);

        assertNull(result);
        verify(postRepository, never()).createPost(any(Post.class));
    }

    @Test
    void getPost_WithValidId_ShouldReturnPost() {
        String validId = "1";
        when(postRepository.getPostById(1L)).thenReturn(testPost);

        Post result = postService.getPost(validId);

        assertNotNull(result);
        assertEquals(testPost, result);
        verify(postRepository).getPostById(1L);
    }

    @Test
    void getPost_WithInvalidId_ShouldReturnNull() {
        String invalidId = "invalid";

        Post result = postService.getPost(invalidId);

        assertNull(result);
        verify(postRepository, never()).getPostById(anyLong());
    }


    @Test
    void updatePost_WithValidParameters_ShouldReturnTrue() {
        String postId = "1";
        String newContent = "Updated content";
        when(postRepository.updatePost(1L, newContent)).thenReturn(true);

        boolean result = postService.updatePost(postId, newContent);

        assertTrue(result);
        verify(postRepository).updatePost(1L, newContent);
    }

    @Test
    void updatePost_WithInvalidPostId_ShouldReturnFalse() {
        String invalidPostId = "invalid";
        String newContent = "Updated content";

        boolean result = postService.updatePost(invalidPostId, newContent);

        assertFalse(result);
        verify(postRepository, never()).updatePost(anyLong(), anyString());
    }

    @Test
    void updatePost_WhenRepositoryReturnsFalse_ShouldReturnFalse() {
        String postId = "1";
        String newContent = "Updated content";
        when(postRepository.updatePost(1L, newContent)).thenReturn(false);

        boolean result = postService.updatePost(postId, newContent);

        assertFalse(result);
        verify(postRepository).updatePost(1L, newContent);
    }

    @Test
    void deletePost_WithValidId_ShouldReturnTrue() {
        String validId = "1";
        when(postRepository.deleteById(1L)).thenReturn(true);

        boolean result = postService.deletePost(validId);

        assertTrue(result);
        verify(postRepository).deleteById(1L);
    }

    @Test
    void deletePost_WithInvalidId_ShouldReturnFalse() {
        String invalidId = "invalid";

        boolean result = postService.deletePost(invalidId);

        assertFalse(result);
        verify(postRepository, never()).deleteById(anyLong());
    }

    @Test
    void deletePost_WhenRepositoryReturnsFalse_ShouldReturnFalse() {
        String validId = "1";
        when(postRepository.deleteById(1L)).thenReturn(false);

        boolean result = postService.deletePost(validId);

        assertFalse(result);
        verify(postRepository).deleteById(1L);
    }
}