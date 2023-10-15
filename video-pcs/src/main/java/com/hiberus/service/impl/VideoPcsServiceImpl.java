package com.hiberus.service.impl;

import com.hiberus.mapper.VideoPcsKafkaValueMapper;
import com.hiberus.service.VideoPcsService;
import com.hiberus.videoEnum.avro.Privacy;
import com.hiberus.videoEnum.avro.Resolution;
import com.hiberus.videoPcs.avro.VideoPcsValue;
import com.hiberus.videoProducer.avro.VideoKey;
import com.hiberus.videoProducer.avro.VideoValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@Slf4j
public class VideoPcsServiceImpl implements VideoPcsService {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    @Value("${environment.video-pcs-topic}")
    private String videoPcsTopic;

    @Autowired
    private VideoPcsKafkaValueMapper videoPcsKafkaValueMapper;

    @Autowired
    private KafkaTemplate<VideoKey, VideoPcsValue> kafkaTemplate;

    @Override
    public void process(VideoKey key, VideoValue value) {
        // Map video & add new fields
        VideoPcsValue pcsValue = videoPcsKafkaValueMapper.videoValueToVideoPcsvalue(value);
        pcsValue.setViews(randomInt(Integer.MAX_VALUE));
        pcsValue.setResolution(randomEnum(Resolution.class));
        pcsValue.setPrivacy(randomEnum(Privacy.class));

        log.info("[videoPcs] Sending video to topic {}", videoPcsTopic);
        kafkaTemplate.send(videoPcsTopic, key, pcsValue);
    }

    private static int randomInt(int bound) {
        return SECURE_RANDOM.nextInt(bound);
    }

    private static <T extends Enum<?>> T randomEnum(Class<T> tClass) {
        return tClass.getEnumConstants()[SECURE_RANDOM.nextInt(tClass.getEnumConstants().length)];
    }

}
