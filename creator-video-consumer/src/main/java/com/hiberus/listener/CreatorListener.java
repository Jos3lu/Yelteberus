package com.hiberus.listener;

import com.hiberus.service.CreatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class CreatorListener {

    @Autowired
    private CreatorService creatorService;

/*
    @Bean
    public Consumer<KStream<CreatorVideoKey, CreatorVideoValue>> process() {
        return creatorVideoKStream -> creatorVideoKStream
                .peek((k, v) -> log.info("Received creator with key: {}", k))
                .peek((k, v) -> creatorServiceService.createChannel());
    }*/

}
