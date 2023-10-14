package com.hiberus.service.impl;

import com.hiberus.mapper.VideoPcsKafkaValueMapper;
import com.hiberus.service.VideoPcsService;
import com.hiberus.videosEnum.avro.Privacy;
import com.hiberus.videosEnum.avro.Resolution;
import com.hiberus.videosPcs.avro.VideoPcsValue;
import com.hiberus.videosProducer.avro.VideoKey;
import com.hiberus.videosProducer.avro.VideoValue;
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
    private KafkaTemplate<VideoKey, VideoValue> kafkaTemplate;

    @Override
    public void process(VideoKey key, VideoValue value) {
        VideoPcsValue videoPcsValue = videoPcsKafkaValueMapper.videoValueToVideoPcsvalue(value);
        videoPcsValue.setViews(randomInt(Integer.MAX_VALUE));
        videoPcsValue.setResolution(randomEnum(Resolution.class));
        videoPcsValue.setPrivacy(randomEnum(Privacy.class));

        log.info("Sending video to topic {}", videoPcsTopic);
        kafkaTemplate.send(videoPcsTopic, key, value);
    }

    private static int randomInt(int bound) {
        return SECURE_RANDOM.nextInt(bound);
    }

    private static <T extends Enum<?>> T randomEnum(Class<T> tClass) {
        return tClass.getEnumConstants()[SECURE_RANDOM.nextInt(tClass.getEnumConstants().length)];
    }

}
