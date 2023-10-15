package com.hiberus.service.impl;


import com.hiberus.creatorProducer.avro.CreatorKey;
import com.hiberus.creatorProducer.avro.CreatorValue;
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

    @Value("${environment.creator-topic}")
    private String creatorTopic;

    @Value("${environment.creator-topic-dlq}")
    private String creatorTopicDLQ;

    @Autowired
    private CreatorKafkaValueMapper creatorKafkaValueMapper;

    @Autowired
    private KafkaTemplate<CreatorKey, CreatorValue> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, CreatorValue> kafkaTemplateDQL;

    @Override
    public void createCreator(Creator creator) throws CreatorNotValidException {
        // Check if creator is valid
        try {
            creator.validCreator();
        } catch (CreatorNotValidException e) {
            String key = "[creatorProducer] Creator " + creator.getCreatorIdentifier() + " not valid";
            log.error(key);
            kafkaTemplateDQL.send(creatorTopicDLQ, key, creatorKafkaValueMapper
                    .creatorToCreatorValue(creator));
            throw new CreatorNotValidException();
        }

        // Generate key & value
        CreatorKey creatorKey = CreatorKey.newBuilder()
                .setCreatorIdentifier(creator.getCreatorIdentifier())
                .build();
        CreatorValue creatorValue = creatorKafkaValueMapper
                .creatorToCreatorValue(creator);

        log.info("[creatorProducer] Sending creator to topic {}", creatorTopic);
        kafkaTemplate.send(creatorTopic, creatorKey, creatorValue);
    }
}
