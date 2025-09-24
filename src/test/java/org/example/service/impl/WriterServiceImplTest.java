package org.example.service.impl;

import org.example.model.Writer;
import org.example.repository.WriterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WriterServiceImplTest {

    @Mock
    private WriterRepository writerRepository;

    private WriterServiceImpl writerService;

    private Writer testWriter;
    private final String testFirstName = "John";
    private final String testLastName = "Doe";

    @BeforeEach
    void setUp() {
        writerService = new WriterServiceImpl(writerRepository);

        testWriter = new Writer();
        testWriter.setId(1L);
        testWriter.setFirstName(testFirstName);
        testWriter.setLastName(testLastName);
    }

    @Test
    void createWriter_WithValidParameters_ShouldReturnTrue() {
        when(writerRepository.createWriter(any(Writer.class))).thenReturn(true);

        boolean result = writerService.createWriter(testFirstName, testLastName);

        assertTrue(result);
        verify(writerRepository).createWriter(any(Writer.class));
    }

    @Test
    void createWriter_WhenRepositoryReturnsFalse_ShouldReturnFalse() {
        when(writerRepository.createWriter(any(Writer.class))).thenReturn(false);

        boolean result = writerService.createWriter(testFirstName, testLastName);

        assertFalse(result);
        verify(writerRepository).createWriter(any(Writer.class));
    }

    @Test
    void getWriter_WithValidId_ShouldReturnWriter() {
        String validId = "1";
        when(writerRepository.getById(1L)).thenReturn(testWriter);

        Writer result = writerService.getWriter(validId);

        assertNotNull(result);
        assertEquals(testWriter, result);
        verify(writerRepository).getById(1L);
    }

    @Test
    void getWriter_WithInvalidId_ShouldReturnNull() {
        String invalidId = "invalid";

        Writer result = writerService.getWriter(invalidId);

        assertNull(result);
        verify(writerRepository, never()).getById(anyLong());
    }

    @Test
    void updateWriter_WithValidParameters_ShouldReturnTrue() {
        String validId = "1";
        String newFirstName = "Jane";
        String newLastName = "Smith";
        when(writerRepository.updateWriter(1L, newFirstName, newLastName)).thenReturn(true);

        boolean result = writerService.updateWriter(validId, newFirstName, newLastName);

        assertTrue(result);
        verify(writerRepository).updateWriter(1L, newFirstName, newLastName);
    }

    @Test
    void updateWriter_WithInvalidId_ShouldReturnFalse() {
        String invalidId = "invalid";
        String newFirstName = "Jane";
        String newLastName = "Smith";

        boolean result = writerService.updateWriter(invalidId, newFirstName, newLastName);

        assertFalse(result);
        verify(writerRepository, never()).updateWriter(anyLong(), anyString(), anyString());
    }

    @Test
    void updateWriter_WhenRepositoryReturnsFalse_ShouldReturnFalse() {
        String validId = "1";
        String newFirstName = "Jane";
        String newLastName = "Smith";
        when(writerRepository.updateWriter(1L, newFirstName, newLastName)).thenReturn(false);

        boolean result = writerService.updateWriter(validId, newFirstName, newLastName);

        assertFalse(result);
        verify(writerRepository).updateWriter(1L, newFirstName, newLastName);
    }

    @Test
    void deleteWriter_WithValidId_ShouldReturnTrue() {
        String validId = "1";
        when(writerRepository.deleteById(1L)).thenReturn(true);

        boolean result = writerService.deleteWriter(validId);

        assertTrue(result);
        verify(writerRepository).deleteById(1L);
    }

    @Test
    void deleteWriter_WithInvalidId_ShouldReturnFalse() {
        String invalidId = "invalid";

        boolean result = writerService.deleteWriter(invalidId);

        assertFalse(result);
        verify(writerRepository, never()).deleteById(anyLong());
    }

    @Test
    void deleteWriter_WhenRepositoryReturnsFalse_ShouldReturnFalse() {
        String validId = "1";
        when(writerRepository.deleteById(1L)).thenReturn(false);

        boolean result = writerService.deleteWriter(validId);

        assertFalse(result);
        verify(writerRepository).deleteById(1L);
    }
}