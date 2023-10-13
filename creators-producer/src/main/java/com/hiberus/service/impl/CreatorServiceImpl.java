package com.hiberus.service.impl;


import com.hiberus.creatorsProducer.avro.CreatorKey;
import com.hiberus.creatorsProducer.avro.CreatorValue;
import com.hiberus.exception.CreatorNotValidException;
import com.hiberus.mapper.CreatorKafkaValueMapper;
import com.hiberus.model.Creator;
import com.hiberus.service.CreatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreatorServiceImpl implements CreatorService {

    @Value("${environment.creators-topic}")
    private String creatorTopic;

    @Autowired
    private CreatorKafkaValueMapper creatorKafkaValueMapper;

    @Autowired
    private KafkaTemplate<CreatorKey, CreatorValue> kafkaTemplate;

    @Override
    public void createCreator(Creator creator) throws CreatorNotValidException {
        // Check if creator is valid
        creator.validCreator();

        // Generate key & value
        CreatorKey creatorKey = CreatorKey.newBuilder()
                .setIdentifier(creator.getIdentifier())
                .build();
        CreatorValue creatorValue = creatorKafkaValueMapper
                .creatorToCreatorValue(creator);

        log.info("Sending creator to topic {}", creatorTopic);
        kafkaTemplate.send(creatorTopic, creatorKey, creatorValue);
    }
}
