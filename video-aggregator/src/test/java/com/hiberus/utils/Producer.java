package com.hiberus.utils;

import com.hiberus.videoPcs.avro.VideoPcsValue;
import com.hiberus.videoProducer.avro.VideoKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    private KafkaTemplate<VideoKey, VideoPcsValue> kafkaTemplate;

    public void send(String topic, VideoKey videoKey, VideoPcsValue videoPcsValue) {
        kafkaTemplate.send(topic, videoKey, videoPcsValue);
    }

}
