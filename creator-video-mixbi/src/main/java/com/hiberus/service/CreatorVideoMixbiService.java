package com.hiberus.service;

import com.hiberus.creatorProducer.avro.CreatorKey;
import com.hiberus.creatorProducer.avro.CreatorValue;
import com.hiberus.creatorVideoMixbi.avro.CreatorVideoMixbiValue;
import com.hiberus.creatorVideoMixbi.avro.CreatorVideoMixbiKey;
import com.hiberus.videoAggregator.avro.VideoAggregatorValue;
import com.hiberus.videoAggregator.avro.VideoAggregatorKey;
import org.apache.kafka.streams.kstream.KStream;

import java.util.function.BiFunction;

public interface CreatorVideoMixbiService {

    /**
     * Join of creator and videos
     *
     * @return Creator associated to the videos
     */
    BiFunction<KStream<CreatorKey, CreatorValue>, KStream<VideoAggregatorKey, VideoAggregatorValue>,
            KStream<CreatorVideoMixbiKey, CreatorVideoMixbiValue>> joiner();

}
