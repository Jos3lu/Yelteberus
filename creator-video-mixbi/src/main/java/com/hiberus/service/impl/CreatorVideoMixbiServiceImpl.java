package com.hiberus.service.impl;

import com.hiberus.creatorProducer.avro.CreatorKey;
import com.hiberus.creatorProducer.avro.CreatorValue;
import com.hiberus.creatorVideoConsumer.avro.CreatorVideoKey;
import com.hiberus.creatorVideoConsumer.avro.CreatorVideoValue;
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
    public BiFunction<KStream<CreatorKey, CreatorValue>, KStream<VideoAggregatorKey, VideoAggregatorValue>, KStream<CreatorVideoKey, CreatorVideoValue>> joiner() {
        return (creatorKStream, videoKStream) -> {

            KTable<CreatorVideoKey, CreatorValue> creatorKTable = creatorKStream
                    .selectKey((k, v) -> CreatorVideoKey.newBuilder()
                            .setCreatorIdentifier(k.getCreatorIdentifier()).build())
                    .toTable(Named.as("CREATOR_MIXBI"), Materialized.as("CREATOR_MIXBI"));

            KTable<CreatorVideoKey, VideoAggregatorValue> videoKTable = videoKStream
                    .selectKey((k, v) -> CreatorVideoKey.newBuilder()
                            .setCreatorIdentifier(k.getCreatorIdentifier()).build())
                    .toTable(Named.as("VIDEO_MIXBI"), Materialized.as("VIDEO_MIXBI"));

            return creatorKTable.join(videoKTable, (creatorValue, videoValue) -> creatorValue == null
                            ? null
                            : CreatorVideoValue.newBuilder()
                            .setCreatorIdentifier(creatorValue.getCreatorIdentifier())
                            .setName(creatorValue.getName())
                            .setSurname(creatorValue.getSurname())
                            .setVideos(videoValue.getVideos())
                            .build())
                    .toStream()
                    .peek((k, v) -> log.info("[creatorVideoMixbi] Creator and videos grouped -> key: {}, value: {}", k, v));
        };
    }

}
