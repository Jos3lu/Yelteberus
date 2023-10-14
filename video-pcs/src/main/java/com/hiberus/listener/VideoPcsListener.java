package com.hiberus.listener;

import com.hiberus.service.VideoPcsService;
import com.hiberus.videoProducer.avro.VideoKey;
import com.hiberus.videoProducer.avro.VideoValue;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class VideoPcsListener {

    @Autowired
    private VideoPcsService videoPcsService;

    @KafkaListener(topics = "videos")
    public void process(ConsumerRecord<VideoKey, VideoValue> video) {
        videoPcsService.process(video.key(), video.value());
    }

}
