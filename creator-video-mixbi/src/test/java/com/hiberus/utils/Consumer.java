package com.hiberus.utils;

import com.hiberus.creatorVideoConsumer.avro.CreatorVideoKey;
import com.hiberus.creatorVideoConsumer.avro.CreatorVideoValue;
import lombok.Getter;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Consumer {

    @Getter
    CreatorVideoKey creatorVideoKey;
    @Getter
    CreatorVideoValue creatorVideoValue;

    @Bean
    public java.util.function.Consumer<KStream<CreatorVideoKey, CreatorVideoValue>> process() {
        return creatorKStream -> creatorKStream
                .peek((k, v) -> {
                    creatorVideoKey = k;
                    creatorVideoValue = v;
                });
    }

}
