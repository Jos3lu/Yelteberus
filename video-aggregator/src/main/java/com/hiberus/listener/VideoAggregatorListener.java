package com.hiberus.listener;

import com.hiberus.service.VideoAggregatorService;
import com.hiberus.videoAggregator.avro.VideoAggregatorKey;
import com.hiberus.videoAggregator.avro.VideoAggregatorValue;
import com.hiberus.videoPcs.avro.VideoPcsValue;
import com.hiberus.videoProducer.avro.VideoKey;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class VideoAggregatorListener {

    @Autowired
    private VideoAggregatorService videoAggregatorService;

    @Bean
    public Function<KStream<VideoKey, VideoPcsValue>, KStream<VideoAggregatorKey, VideoAggregatorValue>> aggregateVideos() {
        return videoAggregatorService.aggregateVideos();
    }

}
