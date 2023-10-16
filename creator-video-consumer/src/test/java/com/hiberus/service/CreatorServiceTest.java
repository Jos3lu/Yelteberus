package com.hiberus.service;

import com.hiberus.model.Creator;
import com.hiberus.model.Video;
import com.hiberus.repository.CreatorRepository;
import com.hiberus.service.impl.CreatorServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("test")
public class CreatorServiceTest {

    @Mock
    CreatorRepository creatorRepository;

    @InjectMocks
    CreatorServiceImpl creatorService;

    @Test
    public void shouldGetCreators() {
        // When
        when(creatorRepository.findAll()).thenReturn(new ArrayList<>());

        // Then
        assertDoesNotThrow(() -> creatorService.getCreators());
        assertEquals(creatorService.getCreators(), new ArrayList<>());
        verify(creatorRepository, times(2)).findAll();
    }

    @Test
    public void shouldSaveCreator() {
        // Given
        Creator creator = new Creator("CREATOR-1", "John", "Smith", new ArrayList<>());

        // When
        when(creatorRepository.save(any(Creator.class))).thenReturn(creator);

        // Then
        assertDoesNotThrow(() -> creatorService.saveCreator(creator));
        verify(creatorRepository, times(1)).save(creator);
    }

    @Test
    public void shouldDeleteCreator() {
        // Given
        String creatorId = "CREATOR-1";

        // When
        doNothing().when(creatorRepository).deleteById(creatorId);

        // Then
        assertDoesNotThrow(() -> creatorService.deleteCreator(creatorId));
        verify(creatorRepository, times(1)).deleteById(creatorId);
    }

    @Test
    public void shouldGetCreator() throws Exception {
        // Given
        Creator creator = new Creator("CREATOR-1", "John", "Smith", new ArrayList<>());
        String creatorId = "CREATOR-1";

        // When
        when(creatorRepository.findById(anyString())).thenReturn(Optional.of(creator));

        // Then
        assertDoesNotThrow(() -> creatorService.getCreator(creatorId));
        assertEquals(creatorService.getCreator(creatorId), creator);
        verify(creatorRepository, times(2)).findById(creatorId);
    }

    @Test
    public void shouldGetVideoCreator() throws Exception {
        // Given
        Video video = new Video("VIDEO-1", "Title", "12:07", LocalDate.now(), "MP4",
                List.of("COMEDY"), "Description", 283725, "HD", "PUBLIC");
        Creator creator = new Creator("CREATOR-1", "John", "Smith", List.of(video));
        String creatorId = "CREATOR-1";
        String videoId = "VIDEO-1";

        // When
        when(creatorRepository.findById(anyString())).thenReturn(Optional.of(creator));

        // Then
        assertDoesNotThrow(() -> creatorService.getVideoCreator(creatorId, videoId));
        assertEquals(creatorService.getVideoCreator(creatorId, videoId), video);
        verify(creatorRepository, times(2)).findById(creatorId);
    }

}
