package com.hiberus.service.impl;

import com.hiberus.component.Aggregator;
import com.hiberus.component.Initializer;
import com.hiberus.service.VideoAggregatorService;
import com.hiberus.videoAggregator.avro.VideoAggregatorKey;
import com.hiberus.videoAggregator.avro.VideoAggregatorValue;
import com.hiberus.videoPcs.avro.VideoPcsValue;
import com.hiberus.videoProducer.avro.VideoKey;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@Slf4j
public class VideoAggregatorServiceImpl implements VideoAggregatorService {

    @Autowired
    private Initializer initializer;

    @Autowired
    private Aggregator aggregator;

    @Override
    public Function<KStream<VideoKey, VideoPcsValue>, KStream<VideoAggregatorKey, VideoAggregatorValue>> aggregateVideos() {
        return videoKeyVideoPcsValueKStream -> videoKeyVideoPcsValueKStream
                .peek((k, v) -> log.info("[videoAggregator] Received video -> key: {}, value: {}", k, v))
                .selectKey((k, v) -> VideoAggregatorKey.newBuilder().setCreatorIdentifier(k.getCreatorIdentifier()).build())
                .groupByKey()
                .aggregate(initializer, aggregator, Named.as("VIDEO_AGGREGATOR"), Materialized.as("VIDEO_AGGREGATOR"))
                .toStream()
                .peek((k, v) -> log.info("[videoAggregator] Grouped videos -> key: {}, value: {}", k, v));
    }

}
