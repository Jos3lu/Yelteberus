package com.hiberus.utils;

import com.hiberus.videoAggregator.avro.VideoAggregatorKey;
import com.hiberus.videoAggregator.avro.VideoAggregatorValue;
import lombok.Getter;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Consumer {

    @Getter
    VideoAggregatorKey videoAggregatorKey;
    @Getter
    VideoAggregatorValue videoAggregatorValue;

    @Bean
    public java.util.function.Consumer<KStream<VideoAggregatorKey, VideoAggregatorValue>> process() {
        return creatorKStream -> creatorKStream
                .peek((k, v) -> {
                    videoAggregatorKey = k;
                    videoAggregatorValue = v;
                });
    }

}
