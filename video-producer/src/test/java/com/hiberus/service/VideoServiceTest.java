package com.hiberus.service;

import com.hiberus.client.ClientCreatorConsumer;
import com.hiberus.dto.VideoResponseDto;
import com.hiberus.mapper.VideoKafkaValueMapper;
import com.hiberus.model.Video;
import com.hiberus.service.impl.VideoServiceImpl;
import com.hiberus.videoEnum.avro.Category;
import com.hiberus.videoEnum.avro.Format;
import com.hiberus.videoProducer.avro.VideoKey;
import com.hiberus.videoProducer.avro.VideoValue;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("test")
public class VideoServiceTest {

    @Mock
    ClientCreatorConsumer clientCreatorConsumer;

    @Mock
    VideoKafkaValueMapper videoKafkaValueMapper;

    @Mock
    KafkaTemplate<VideoKey, VideoValue> kafkaTemplate;

    @Mock
    KafkaTemplate<String, VideoValue> kafkaTemplateDQL;

    @InjectMocks
    private VideoServiceImpl videoService;

    private Video video = new Video("VIDEO-1", "CREATOR-1", "Video-1",
            "02:23:12", LocalDate.parse("2018-07-12"), Format.MP4,
            Arrays.asList(Category.ANIME), "Description of video");

    @Test
    public void shouldCreateACreator(){
        // When
        when(videoKafkaValueMapper.videoToVideoValue(any(Video.class))).thenReturn(new VideoValue());

        // Then
        assertDoesNotThrow(() -> videoService.createVideo(video));
    }

    @Test
    public void shouldUpdateCreator() {
        // When
        when(videoKafkaValueMapper.videoToVideoValue(any(Video.class))).thenReturn(new VideoValue());
        when(clientCreatorConsumer.getVideoCreator(anyString(), anyString()))
                .thenReturn(new ResponseEntity<>(VideoResponseDto.builder().build(), HttpStatus.ACCEPTED));

        // Then
        assertDoesNotThrow(() -> videoService.updateVideo(video.getVideoIdentifier(), video.getCreatorIdentifier(), video));
    }

}
