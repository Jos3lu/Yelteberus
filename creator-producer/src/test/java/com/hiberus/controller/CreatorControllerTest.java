package com.hiberus.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hiberus.dto.CreatorRequestDto;
import com.hiberus.model.Creator;
import com.hiberus.service.impl.CreatorServiceImpl;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
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
    public void createCreatorShouldReturn202() throws Exception {
        // Given
        CreatorRequestDto creatorRequestDto = CreatorRequestDto.builder()
                .creatorIdentifier("CREATOR-1").name("John").surname("Smith")
                .birth(LocalDate.parse("1977-07-07"))
                .email("john@gmail.com").phone("+34 384 83 47 39").build();

        // When
        doNothing().when(creatorService).createCreator(any(Creator.class));

        // Then
        mockMvc.perform(post("/api/creators/").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(creatorRequestDto)))
                .andExpect(status().isAccepted());
    }

    @Test
    public void updateCreatorShouldReturn202() throws Exception {
        // Given
        CreatorRequestDto creatorRequestDto = CreatorRequestDto.builder()
                .creatorIdentifier("CREATOR-1").name("John").surname("Smith")
                .birth(LocalDate.parse("1977-07-07"))
                .email("john@hotmail.com").phone("+47 734 36 28 47").build();
        String creatorId = "CREATOR-1";

        // When
        doNothing().when(creatorService).updateCreator(anyString(), any(Creator.class));

        // Then
        mockMvc.perform(put("/api/creators/{creatorId}", creatorId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creatorRequestDto)))
                .andExpect(status().isAccepted());
    }

    @Test
    public void deleteCreatorShouldReturn202() throws Exception {
        // Given
        String creatorId = "CREATOR-1";

        // When
        doNothing().when(creatorService).deleteCreator(anyString());

        // Then
        mockMvc.perform(delete("/api/creators/{creatorId}", creatorId))
                .andExpect(status().isAccepted());
    }

}
