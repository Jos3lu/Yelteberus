package com.hiberus.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hiberus.model.Creator;
import com.hiberus.model.Video;
import com.hiberus.service.impl.CreatorServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
public class CreatorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CreatorServiceImpl creatorService;

    @BeforeAll
    public void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void getCreatorsShouldReturn200() throws Exception {
        // When
        when(creatorService.getCreators()).thenReturn(new ArrayList<>());

        // Then
        mockMvc.perform(get("/api/creators"))
                .andExpect(status().isOk());
    }

    @Test
    public void getCreatorShouldReturn200() throws Exception {
        // Given
        String creatorId = "CREATOR-1";
        Creator creator = new Creator("CREATOR-1", "John", "Smith", new ArrayList<>());

        // When
        when(creatorService.getCreator(anyString())).thenReturn(creator);

        // Then
        mockMvc.perform(get("/api/creators/{creatorId}", creator))
                .andExpect(status().isOk());
    }

    @Test
    public void getVideoCreatorShouldReturn200() throws Exception {
        // Given
        Video video = new Video("VIDEO-1", "Title", "12:07", LocalDate.now(), "MP4",
                List.of("COMEDY"), "Description", 283725, "HD", "PUBLIC");
        String creatorId = "CREATOR-1";
        String videoId = "VIDEO-1";

        // When
        when(creatorService.getVideoCreator(anyString(), anyString())).thenReturn(video);

        // Then
        mockMvc.perform(get("/api/creators/{creatorId}/videos/{videoId}", creatorId, videoId))
                .andExpect(status().isOk());
    }

}
