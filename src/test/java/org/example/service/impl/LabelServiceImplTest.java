package org.example.service.impl;

import org.example.model.Label;
import org.example.repository.LabelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LabelServiceImplTest {

    @Mock
    private LabelRepository labelRepository;

    private LabelServiceImpl labelService;

    private Label testLabel;

    @BeforeEach
    void setUp() {
        labelService = new LabelServiceImpl(labelRepository);
        
        testLabel = new Label();
        testLabel.setId(1L);
        testLabel.setName("Test Label");
    }

    @Test
    void getLabelById_WithValidNumericId_ShouldReturnLabel() {
        String validId = "123";
        when(labelRepository.getLabelById(123L)).thenReturn(testLabel);
        
        Label result = labelService.getLabelById(validId);

        assertNotNull(result);
        assertEquals(testLabel, result);
        verify(labelRepository).getLabelById(123L);
    }

    @Test
    void getLabelById_WithInvalidNumericId_ShouldReturnNull() {
        String invalidId = "abc";

        Label result = labelService.getLabelById(invalidId);

        assertNull(result);
        verify(labelRepository, never()).getLabelById(anyLong());
    }

    @Test
    void createLabel_WithValidLabel_ShouldReturnLabelId() {
        Long newLabelId = 123L;
        String labelName = "New Label";
        when(labelRepository.createLabel(any(Label.class))).thenReturn(newLabelId);
        
        Long result = labelService.createLabel(labelName);

        assertEquals(result, newLabelId);
        verify(labelRepository).createLabel(any(Label.class));
    }

    @Test
    void deleteLabel_WithValidLabel_ShouldReturnTrue() {
        String labelToDelete = "Label to delete";
        when(labelRepository.deleteLabel(labelToDelete)).thenReturn(true);

        boolean result = labelService.deleteLabel(labelToDelete);

        assertTrue(result);
        verify(labelRepository).deleteLabel(labelToDelete);
    }

    @Test
    void updateLabel_WithValidParameters_ShouldReturnTrue() {
        String postId = "456";
        String newLabel = "Updated Label";
        when(labelRepository.updateLabel(456L, newLabel)).thenReturn(true);
        
        boolean result = labelService.updateLabel(postId, newLabel);

        assertTrue(result);
        verify(labelRepository).updateLabel(456L, newLabel);
    }

    @Test
    void updateLabel_WithInvalidPostId_ShouldReturnFalse() {
        String invalidPostId = "invalid";
        String newLabel = "Updated Label";
        
        boolean result = labelService.updateLabel(invalidPostId, newLabel);

        assertFalse(result);
        verify(labelRepository, never()).updateLabel(anyLong(), anyString());
    }
}