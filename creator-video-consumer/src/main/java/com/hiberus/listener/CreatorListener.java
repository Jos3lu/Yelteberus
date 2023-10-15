package com.hiberus.listener;

import com.hiberus.creatorVideoConsumer.avro.CreatorVideoKey;
import com.hiberus.creatorVideoConsumer.avro.CreatorVideoValue;
import com.hiberus.mapper.CreatorMapper;
import com.hiberus.service.CreatorService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class CreatorListener {

    @Autowired
    private CreatorService creatorService;

    @Autowired
    private CreatorMapper creatorMapper;

    @Bean
    public Consumer<KStream<CreatorVideoKey, CreatorVideoValue>> process() {
        return creatorKStream -> creatorKStream
                .peek((k, v) -> log.info("Received creator with key: {}", k))
                .peek((k, v) -> {
                    if (v == null)
                        creatorService.deleteCreator(k.getCreatorIdentifier());
                    else
                        creatorService.saveCreator(creatorMapper.creatorVideoValueToCreator(v));
                });
    }

}
