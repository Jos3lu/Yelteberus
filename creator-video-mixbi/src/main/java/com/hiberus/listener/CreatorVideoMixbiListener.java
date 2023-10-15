package com.hiberus.listener;

import com.hiberus.creatorProducer.avro.CreatorKey;
import com.hiberus.creatorProducer.avro.CreatorValue;
import com.hiberus.creatorVideoConsumer.avro.CreatorVideoKey;
import com.hiberus.creatorVideoConsumer.avro.CreatorVideoValue;
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
            KStream<CreatorVideoKey, CreatorVideoValue>> joiner() {
        return creatorVideoMixbiService.joiner();
    }

}
