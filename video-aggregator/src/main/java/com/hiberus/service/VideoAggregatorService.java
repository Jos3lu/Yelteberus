package com.hiberus.service;

import com.hiberus.videoAggregator.avro.VideoAggregatorKey;
import com.hiberus.videoAggregator.avro.VideoAggregatorValue;
import com.hiberus.videoPcs.avro.VideoPcsValue;
import com.hiberus.videoProducer.avro.VideoKey;
import org.apache.kafka.streams.kstream.KStream;

import java.util.function.Function;

public interface VideoAggregatorService {

    /**
     * Aggregate videos by Creator ID
     *
     * @return Array of videos grouped by creator ID
     */
    Function<KStream<VideoKey, VideoPcsValue>, KStream<VideoAggregatorKey, VideoAggregatorValue>> aggregateVideos();

}
