package com.hiberus.service;

import com.hiberus.creatorProducer.avro.CreatorKey;
import com.hiberus.creatorProducer.avro.CreatorValue;
import com.hiberus.creatorVideoConsumer.avro.CreatorVideoKey;
import com.hiberus.creatorVideoConsumer.avro.CreatorVideoValue;
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
            KStream<CreatorVideoKey, CreatorVideoValue>> joiner();

}
