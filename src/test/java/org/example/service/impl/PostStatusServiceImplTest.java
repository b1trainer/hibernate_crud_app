package org.example.service.impl;

import org.example.Status;
import org.example.model.PostStatus;
import org.example.repository.PostStatusRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostStatusServiceImplTest {

    @Mock
    private PostStatusRepository postStatusRepository;

    private PostStatusServiceImpl postStatusService;

    private PostStatus testPostStatus;
    private final Long testPostId = 1L;

    @BeforeEach
    void setUp() {
        postStatusService = new PostStatusServiceImpl(postStatusRepository);

        testPostStatus = new PostStatus();
        testPostStatus.setId(testPostId);
        testPostStatus.setStatus(Status.UNDER_REVIEW.getValue());
    }

    @Test
    void getPostStatus_WithValidPostId_ShouldReturnPostStatus() {
        String validPostId = "1";
        when(postStatusRepository.getById(1L)).thenReturn(testPostStatus);

        PostStatus result = postStatusService.getPostStatus(validPostId);

        assertNotNull(result);
        assertEquals(testPostStatus, result);
        verify(postStatusRepository).getById(1L);
    }

    @Test
    void getPostStatus_WithInvalidPostId_ShouldReturnNull() {
        String invalidPostId = "invalid";

        PostStatus result = postStatusService.getPostStatus(invalidPostId);

        assertNull(result);
        verify(postStatusRepository, never()).getById(anyLong());
    }

    @Test
    void updatePostStatus_WithValidParameters_ShouldReturnTrue() {

        String validPostId = "1";
        Status status = Status.UNDER_REVIEW;
        when(postStatusRepository.updatePostStatus(1L, status.getValue())).thenReturn(true);

        boolean result = postStatusService.updatePostStatus(validPostId, status);

        assertTrue(result);
        verify(postStatusRepository).updatePostStatus(1L, status.getValue());
    }

    @Test
    void updatePostStatus_WithInvalidPostId_ShouldReturnFalse() {
        String invalidPostId = "invalid";
        Status status = Status.UNDER_REVIEW;

        boolean result = postStatusService.updatePostStatus(invalidPostId, status);

        assertFalse(result);
        verify(postStatusRepository, never()).updatePostStatus(anyLong(), anyString());
    }

    @Test
    void updatePostStatus_WhenRepositoryReturnsFalse_ShouldReturnFalse() {
        String validPostId = "1";
        Status status = Status.UNDER_REVIEW;
        when(postStatusRepository.updatePostStatus(1L, status.getValue())).thenReturn(false);

        boolean result = postStatusService.updatePostStatus(validPostId, status);

        assertFalse(result);
        verify(postStatusRepository).updatePostStatus(1L, status.getValue());
    }
}