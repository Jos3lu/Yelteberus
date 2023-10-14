package com.hiberus.service.impl;

import com.hiberus.creatorProducer.avro.CreatorKey;
import com.hiberus.creatorProducer.avro.CreatorValue;
import com.hiberus.creatorVideoMixbi.avro.CreatorVideoMixbiValue;
import com.hiberus.creatorVideoMixbi.avro.CreatorVideoMixbiKey;
import com.hiberus.service.CreatorVideoMixbiService;
import com.hiberus.videoAggregator.avro.VideoAggregatorValue;
import com.hiberus.videoAggregator.avro.VideoAggregatorKey;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Named;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;

@Service
@Slf4j
public class CreatorVideoMixbiServiceImpl implements CreatorVideoMixbiService {

    @Override
    public BiFunction<KStream<CreatorKey, CreatorValue>, KStream<VideoAggregatorKey, VideoAggregatorValue>, KStream<CreatorVideoMixbiKey, CreatorVideoMixbiValue>> joiner() {
        return (creatorKStream, videoKStream) -> {

            KTable<CreatorVideoMixbiKey, CreatorValue> creatorKTable = creatorKStream
                    .selectKey((k, v) -> CreatorVideoMixbiKey.newBuilder()
                            .setCreatorIdentifier(k.getCreatorIdentifier()).build())
                    .toTable(Named.as("CREATOR_MIXBI"), Materialized.as("CREATOR_MIXBI"));

            KTable<CreatorVideoMixbiKey, VideoAggregatorValue> videoKTable = videoKStream
                    .selectKey((k, v) -> CreatorVideoMixbiKey.newBuilder()
                            .setCreatorIdentifier(k.getCreatorIdentifier()).build())
                    .toTable(Named.as("VIDEO_MIXBI"), Materialized.as("VIDEO_MIXBI"));

            return creatorKTable.join(videoKTable, (creatorValue, videoValue) -> CreatorVideoMixbiValue.newBuilder()
                            .setCreatorIdentifier(creatorValue.getCreatorIdentifier())
                            .setName(creatorValue.getName())
                            .setSurname(creatorValue.getSurname())
                            .setVideos(videoValue.getVideos())
                            .build())
                    .toStream()
                    .peek((k, v) -> log.info("Created join of creator and videos -> key: {}, value: {}", k, v));
        };
    }

}
