package com.hiberus.mapper;


import com.hiberus.creatorProducer.avro.CreatorValue;
import com.hiberus.model.Creator;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreatorKafkaValueMapper {
    CreatorValue creatorToCreatorValue(Creator creator);
}
