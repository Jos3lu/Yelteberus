package com.hiberus.utils;

import com.hiberus.creatorProducer.avro.CreatorKey;
import com.hiberus.creatorProducer.avro.CreatorValue;
import com.hiberus.videoAggregator.avro.VideoAggregatorKey;
import com.hiberus.videoAggregator.avro.VideoAggregatorValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    private KafkaTemplate<CreatorKey, CreatorValue> kafkaTemplateCreator;

    @Autowired
    private KafkaTemplate<VideoAggregatorKey, VideoAggregatorValue> kafkaTemplateVideo;

    public void sendCreator(String topic, CreatorKey creatorKey, CreatorValue creatorValue) {
        kafkaTemplateCreator.send(topic, creatorKey, creatorValue);
    }

    public void sendVideo(String topic, VideoAggregatorKey videoAggregatorKey, VideoAggregatorValue videoAggregatorValue) {
        kafkaTemplateVideo.send(topic, videoAggregatorKey, videoAggregatorValue);
    }

}
