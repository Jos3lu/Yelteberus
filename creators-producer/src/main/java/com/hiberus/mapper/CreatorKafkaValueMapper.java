package com.hiberus.mapper;

import com.hiberus.creatorsProducer.avro.CreatorValue;
import com.hiberus.model.Creator;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreatorKafkaValueMapper {
    CreatorValue creatorToCreatorValue(Creator creator);
}
