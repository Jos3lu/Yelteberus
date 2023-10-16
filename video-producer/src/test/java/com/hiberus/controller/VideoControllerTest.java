package com.hiberus.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hiberus.dto.VideoRequestDto;
import com.hiberus.model.Video;
import com.hiberus.service.impl.VideoServiceImpl;
import com.hiberus.videoEnum.avro.Category;
import com.hiberus.videoEnum.avro.Format;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class VideoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    VideoServiceImpl videoService;

    @BeforeAll
    public void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void createVideoShouldReturn202() throws Exception {
        // Given
        VideoRequestDto videoRequestDto = VideoRequestDto.builder()
                .videoIdentifier("VIDEO-1").creatorIdentifier("CREATOR-1").title("Title")
                .categories(Arrays.asList(Category.MUSIC)).format(Format.AVI).duration("23:15")
                .uploadDate(LocalDate.now()).description("Description").build();

        // When
        doNothing().when(videoService).createVideo(any(Video.class));

        // Then
        mockMvc.perform(post("/api/videos/").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(videoRequestDto)))
                .andExpect(status().isAccepted());
    }

    @Test
    public void updateVideoShouldReturn202() throws Exception {
        // Given
        VideoRequestDto videoRequestDto = VideoRequestDto.builder()
                .videoIdentifier("VIDEO-1").creatorIdentifier("CREATOR-1").title("Title")
                .categories(Arrays.asList(Category.MUSIC)).format(Format.AVI).duration("23:15")
                .uploadDate(LocalDate.now()).description("Description").build();
        String videoId = videoRequestDto.getVideoIdentifier();
        String creatorId = videoRequestDto.getCreatorIdentifier();

        // When
        doNothing().when(videoService).updateVideo(anyString(), anyString(), any(Video.class));

        // Then
        mockMvc.perform(put("/api/videos/{videoId}/creators/{creatorId}", videoId, creatorId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(videoRequestDto)))
                .andExpect(status().isAccepted());
    }

}
