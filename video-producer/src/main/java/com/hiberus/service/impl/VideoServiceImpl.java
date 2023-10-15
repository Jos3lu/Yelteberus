package com.hiberus.service.impl;

import com.hiberus.client.ClientCreatorConsumer;
import com.hiberus.dto.VideoResponseDto;
import com.hiberus.exception.VideoNotFoundException;
import com.hiberus.exception.VideoNotValidException;
import com.hiberus.mapper.VideoKafkaValueMapper;
import com.hiberus.model.Video;
import com.hiberus.service.VideoService;
import com.hiberus.videoProducer.avro.VideoKey;
import com.hiberus.videoProducer.avro.VideoValue;
import feign.FeignException;
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

    @Value("${environment.video-topic-dlq}")
    private String videoTopicDLQ;

    @Autowired
    private ClientCreatorConsumer clientCreatorConsumer;

    @Autowired
    private VideoKafkaValueMapper videoKafkaValueMapper;

    @Autowired
    private KafkaTemplate<VideoKey, VideoValue> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, VideoValue> kafkaTemplateDLQ;

    @Override
    public void createVideo(Video video) throws VideoNotValidException {
        // Check if video is valid
        try {
            video.validVideo();
        } catch (VideoNotValidException e) {
            String key = "Video '" + video.getVideoIdentifier() + " of creator " +
                    video.getCreatorIdentifier() + "' not valid";
            log.error("[videoProducer] " + key);
            kafkaTemplateDLQ.send(videoTopicDLQ, key, videoKafkaValueMapper
                    .videoToVideoValue(video));
            throw new VideoNotValidException();
        }

        // Generate key & value
        VideoKey videoKey = VideoKey.newBuilder()
                .setVideoIdentifier(video.getVideoIdentifier())
                .setCreatorIdentifier(video.getCreatorIdentifier())
                .build();
        VideoValue videoValue = videoKafkaValueMapper
                .videoToVideoValue(video);

        log.info("[videoProducer] Sending video to topic {}", videoTopic);
        kafkaTemplate.send(videoTopic, videoKey, videoValue);
    }

    @Override
    public VideoResponseDto getVideoCreator(String creatorId, String videoId) throws VideoNotFoundException {
        try {
            return clientCreatorConsumer.getVideoCreator(creatorId, videoId).getBody();
        } catch (FeignException.NotFound e) {
            throw new VideoNotFoundException(videoId);
        }
    }

    @Override
    public void updateVideo(String creatorId, String videoId, Video video) throws VideoNotValidException, VideoNotFoundException {
        // Get video (check if exists)
        try {
            getVideoCreator(creatorId, videoId);
        } catch (VideoNotFoundException e) {
            String key = "Video " + videoId + " not found";
            log.error("[videoProducer] " + key);
            kafkaTemplateDLQ.send(videoTopicDLQ, key, videoKafkaValueMapper
                    .videoToVideoValue(video));
            throw new VideoNotFoundException(videoId);
        }

        // Check if video is valid
        try {
            video.validVideo();
        } catch (VideoNotValidException e) {
            String key = "Video '" + video.getVideoIdentifier() + " of creator " +
                    video.getCreatorIdentifier() + "' not valid";
            log.error("[videoProducer] " + key);
            kafkaTemplateDLQ.send(videoTopicDLQ, key, videoKafkaValueMapper
                    .videoToVideoValue(video));
            throw new VideoNotValidException();
        }

        // Generate key & value
        VideoKey videoKey = VideoKey.newBuilder()
                .setVideoIdentifier(video.getVideoIdentifier())
                .setCreatorIdentifier(video.getCreatorIdentifier())
                .build();
        VideoValue videoValue = videoKafkaValueMapper
                .videoToVideoValue(video);

        log.info("[videoProducer] Sending video to topic {}", videoTopic);
        kafkaTemplate.send(videoTopic, videoKey, videoValue);
    }

}
