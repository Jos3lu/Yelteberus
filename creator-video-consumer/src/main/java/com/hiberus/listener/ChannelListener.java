package com.hiberus.listener;

import com.hiberus.mapper.ChannelMapper;
import com.hiberus.model.Channel;
import com.hiberus.service.ChannelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ChannelListener {

    @Autowired
    private ChannelService channelService;

    @Autowired
    private ChannelMapper channelMapper;

    @Bean
    public Consumer<KStream<CreatorVideoKey, CreatorVideoValue>> process() {
        return creatorVideoKStream -> creatorVideoKStream
                .peek((k, v) -> log.info("Received creator with key: {}", k))
                .peek((k, v) -> channelService.createChannel());
    }

}
