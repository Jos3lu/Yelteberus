package com.hiberus.service.impl;


import com.hiberus.client.ClientCreatorConsumer;
import com.hiberus.creatorProducer.avro.CreatorKey;
import com.hiberus.creatorProducer.avro.CreatorValue;
import com.hiberus.dto.CreatorResponseDto;
import com.hiberus.exception.CreatorNotFoundException;
import com.hiberus.exception.CreatorNotValidException;
import com.hiberus.mapper.CreatorKafkaValueMapper;
import com.hiberus.model.Creator;
import com.hiberus.service.CreatorService;
import feign.FeignException;
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
    private ClientCreatorConsumer clientCreatorConsumer;

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
            String key = "Creator " + creator.getCreatorIdentifier() + " not valid";
            log.error("[creatorProducer] " + key);
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

    @Override
    public CreatorResponseDto getCreator(String creatorId) throws CreatorNotFoundException {
        try {
            return clientCreatorConsumer.getCreator(creatorId).getBody();
        } catch (FeignException.NotFound e) {
            throw new CreatorNotFoundException(creatorId);
        }
    }

    @Override
    public void updateCreator(String creatorId, Creator creator) throws CreatorNotFoundException, CreatorNotValidException {
        // Get creator (check if exists)
        try {
            getCreator(creatorId);
        } catch (CreatorNotFoundException e) {
            String key = "Creator " + creatorId + " not found";
            log.error("[creatorProducer] " + key);
            kafkaTemplateDQL.send(creatorTopicDLQ, key, creatorKafkaValueMapper
                    .creatorToCreatorValue(creator));
            throw new CreatorNotFoundException(creatorId);
        }

        // Check if creator is valid
        try {
            creator.validCreator();
        } catch (CreatorNotValidException e) {
            String key = "Creator " + creatorId + " not valid";
            log.error("[creatorProducer] " + key);
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

    @Override
    public void deleteCreator(String creatorId) {
        CreatorKey creatorKey = CreatorKey.newBuilder()
                .setCreatorIdentifier(creatorId)
                .build();

        log.info("Sending request of delete to topic {}", creatorTopic);
        kafkaTemplate.send(creatorTopic, creatorKey, null);
    }
}
