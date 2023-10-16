package com.hiberus.service;

import com.hiberus.mapper.VideoPcsKafkaValueMapper;
import com.hiberus.service.impl.VideoPcsServiceImpl;
import com.hiberus.videoPcs.avro.VideoPcsValue;
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
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("test")
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
public class VideoPcsServiceTest {

    @Mock
    VideoPcsKafkaValueMapper videoPcsKafkaValueMapper;

    @Mock
    KafkaTemplate<VideoKey, VideoValue> kafkaTemplate;

    @InjectMocks
    VideoPcsServiceImpl videoPcsService;

    @Test
    public void shouldProcessVideos() {
        // Given
        VideoKey videoKey = VideoKey.newBuilder().setVideoIdentifier("VIDEO-1")
                .setCreatorIdentifier("CREATOR-1").build();
        VideoValue videoValue = VideoValue.newBuilder().setVideoIdentifier("VIDEO-1")
                .setTitle("Title").setDescription("Description").build();
        VideoPcsValue videoPcsValue = VideoPcsValue.newBuilder().setVideoIdentifier("VIDEO-1")
                .setTitle("Title").setDescription("Description").build();

        // When
        when(videoPcsKafkaValueMapper.videoValueToVideoPcsvalue(any(VideoValue.class)))
                .thenReturn(videoPcsValue);

        // Then
        assertDoesNotThrow(() -> videoPcsService.process(videoKey, videoValue));
    }

}
