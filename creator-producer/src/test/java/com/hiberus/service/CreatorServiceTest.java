package com.hiberus.service;

import com.hiberus.client.ClientCreatorConsumer;
import com.hiberus.creatorProducer.avro.CreatorKey;
import com.hiberus.creatorProducer.avro.CreatorValue;
import com.hiberus.dto.CreatorResponseDto;
import com.hiberus.mapper.CreatorKafkaValueMapper;
import com.hiberus.model.Creator;
import com.hiberus.service.impl.CreatorServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("test")
public class CreatorServiceTest {

    @Mock
    ClientCreatorConsumer clientCreatorConsumer;

    @Mock
    CreatorKafkaValueMapper creatorKafkaValueMapper;

    @Mock
    KafkaTemplate<CreatorKey, CreatorValue> kafkaTemplate;

    @Mock
    KafkaTemplate<String, CreatorValue> kafkaTemplateDQL;

    @InjectMocks
    CreatorServiceImpl creatorService;

    @Test
    public void shouldCreateACreator() {
        // Given
        Creator creator = Creator.builder().creatorIdentifier("CREATOR-1").name("John")
                .surname("Smith").birth(LocalDate.parse("1977-07-07"))
                .email("john@gmail.com").phone("+34 384 37 48 37").build();
        CreatorValue creatorValue = CreatorValue.newBuilder().setCreatorIdentifier("CREATOR-1")
                .setName("John").setSurname("Smith").setBirth(LocalDate.parse("1977-07-07"))
                .setEmail("john@gmail.com").setPhone("+34 384 48 37").build();

        // When
        when(creatorKafkaValueMapper.creatorToCreatorValue(any(Creator.class))).thenReturn(creatorValue);

        // Then
        assertDoesNotThrow(() -> creatorService.createCreator(creator));
    }

    @Test
    public void shouldUpdateCreator() {
        // Given
        Creator creator = Creator.builder().creatorIdentifier("CREATOR-1").name("John")
                .surname("Smith").birth(LocalDate.parse("1977-07-07"))
                .email("john@hotmail.com").phone("+34 847 36 38 62").build();
        CreatorValue creatorValue = CreatorValue.newBuilder().setCreatorIdentifier("CREATOR-1")
                .setName("John").setSurname("Smith").setBirth(LocalDate.parse("1977-07-07"))
                .setEmail("john@hotmail.com").setPhone("+34 847 36 38 62").build();

        // When
        when(creatorKafkaValueMapper.creatorToCreatorValue(any(Creator.class))).thenReturn(creatorValue);
        when(clientCreatorConsumer.getCreator(anyString()))
                .thenReturn(new ResponseEntity<>(CreatorResponseDto.builder().build(), HttpStatus.ACCEPTED));

        // Then
        assertDoesNotThrow(() -> creatorService.updateCreator(creator.getCreatorIdentifier(), creator));
    }

    @Test
    public void shouldDeleteCreator() {
        // Given
        String creatorId = "CREATOR-1";

        // Then
        assertDoesNotThrow(() -> creatorService.deleteCreator(creatorId));
    }

}
