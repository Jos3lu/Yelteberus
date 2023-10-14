package com.hiberus.component;

import com.hiberus.mapper.VideoKafkaMapper;
import com.hiberus.videoAggregator.avro.Video;
import com.hiberus.videoAggregator.avro.VideoAggregatorKey;
import com.hiberus.videoAggregator.avro.VideoAggregatorValue;
import com.hiberus.videoPcs.avro.VideoPcsValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class Aggregator implements org.apache.kafka.streams.kstream.Aggregator<VideoAggregatorKey, VideoPcsValue, VideoAggregatorValue> {

    @Autowired
    private VideoKafkaMapper videoKafkaMapper;

    @Override
    public VideoAggregatorValue apply(VideoAggregatorKey videoAggregatorKey, VideoPcsValue videoPcsValue, VideoAggregatorValue videoAggregatorValue) {
        // Delete video so we don't have duplicates (if we receive the same video but the information is updated)
        videoAggregatorValue = VideoAggregatorValue.newBuilder()
                .setVideos(videoAggregatorValue.getVideos()
                        .stream()
                        .filter(video -> !videoPcsValue.getVideoIdentifier().equals(video.getVideoIdentifier()))
                        .collect(Collectors.toList())).build();

        // Add video & return list
        videoAggregatorValue.getVideos().add(createVideo(videoPcsValue));
        return videoAggregatorValue;
    }

    private Video createVideo(VideoPcsValue videoPcsValue) {
        return videoKafkaMapper.videoPcsValueToVideo(videoPcsValue);
    }

}