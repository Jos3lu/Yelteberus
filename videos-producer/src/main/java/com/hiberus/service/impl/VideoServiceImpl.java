package com.hiberus.service.impl;

import com.hiberus.exception.VideoNotValidException;
import com.hiberus.mapper.VideoKafkaValueMapper;
import com.hiberus.model.Video;
import com.hiberus.service.VideoService;
import com.hiberus.videosProducer.avro.VideoKey;
import com.hiberus.videosProducer.avro.VideoValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VideoServiceImpl implements VideoService {

    @Value("${environment.video-topic}")
    private String videoTopic;

    @Autowired
    private VideoKafkaValueMapper videoKafkaValueMapper;

    @Autowired
    private KafkaTemplate<VideoKey, VideoValue> kafkaTemplate;

    @Override
    public void createVideo(Video video) throws VideoNotValidException {
        // Check if video is valid
        video.validVideo();

        // Generate key & value
        VideoKey videoKey = VideoKey.newBuilder()
                .setIdentifier(video.getIdentifier())
                .build();
        VideoValue videoValue = videoKafkaValueMapper
                .videoToVideoValue(video);

        log.info("Sending video to topic {}", videoTopic);
        kafkaTemplate.send(videoTopic, videoKey, videoValue);
    }

}
