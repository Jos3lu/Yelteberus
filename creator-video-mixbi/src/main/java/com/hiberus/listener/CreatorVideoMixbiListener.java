package com.hiberus.listener;

import com.hiberus.creatorProducer.avro.CreatorKey;
import com.hiberus.creatorProducer.avro.CreatorValue;
import com.hiberus.creatorVideoMixbi.avro.CreatorVideoMixbiValue;
import com.hiberus.creatorVideoMixbi.avro.CreatorVideoMixbiKey;
import com.hiberus.service.CreatorVideoMixbiService;
import com.hiberus.videoAggregator.avro.VideoAggregatorValue;
import com.hiberus.videoAggregator.avro.VideoAggregatorKey;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.BiFunction;

@Configuration
public class CreatorVideoMixbiListener {

    @Autowired
    private CreatorVideoMixbiService creatorVideoMixbiService;

    @Bean
    public BiFunction<KStream<CreatorKey, CreatorValue>, KStream<VideoAggregatorKey, VideoAggregatorValue>,
            KStream<CreatorVideoMixbiKey, CreatorVideoMixbiValue>> joiner() {
        return creatorVideoMixbiService.joiner();
    }

}
