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
import java.util.List;

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
    VideoServiceImpl videoService;

    @Test
    public void shouldCreateAVideo(){
        // Given
        Video video = Video.builder().videoIdentifier("VIDEO-1").creatorIdentifier("CREATOR-1")
                .title("Video-1").duration("02:23:12").uploadDate(LocalDate.now()).format(Format.AVI)
                .categories(List.of(Category.ANIME)).description("Description of video").build();
        VideoValue videoValue = VideoValue.newBuilder().setVideoIdentifier("VIDEO-1").setTitle("Video-1")
                .setDuration("02:23:12").setUploadDate(LocalDate.now()).setFormat(Format.AVI)
                .setCategories(List.of(Category.ANIME)).setDescription("Description of video").build();

        // When
        when(videoKafkaValueMapper.videoToVideoValue(any(Video.class))).thenReturn(videoValue);

        // Then
        assertDoesNotThrow(() -> videoService.createVideo(video));
    }

    @Test
    public void shouldUpdateVideo() {
        // Given
        Video video = Video.builder().videoIdentifier("VIDEO-1").creatorIdentifier("CREATOR-1")
                .title("Video-1.1").duration("02:23:12").uploadDate(LocalDate.now()).format(Format.MP4)
                .categories(List.of(Category.ANIME, Category.ANIMATION)).description("Description of video").build();
        VideoValue videoValue = VideoValue.newBuilder().setVideoIdentifier("VIDEO-1").setTitle("Video-1")
                .setDuration("02:23:12").setUploadDate(LocalDate.now()).setFormat(Format.AVI)
                .setCategories(List.of(Category.ANIME)).setDescription("Description of video").build();

        // When
        when(videoKafkaValueMapper.videoToVideoValue(any(Video.class))).thenReturn(videoValue);
        when(clientCreatorConsumer.getVideoCreator(anyString(), anyString()))
                .thenReturn(new ResponseEntity<>(VideoResponseDto.builder().build(), HttpStatus.ACCEPTED));

        // Then
        assertDoesNotThrow(() -> videoService.updateVideo(video.getVideoIdentifier(), video.getCreatorIdentifier(), video));
    }

}
