package com.hiberus.mapper;

import com.hiberus.creatorVideoConsumer.avro.CreatorVideoKey;
import com.hiberus.creatorVideoConsumer.avro.CreatorVideoValue;
import com.hiberus.dto.CreatorDto;
import com.hiberus.model.Creator;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreatorMapper {
    CreatorDto creatorToCreatorDto(Creator creator);
    Creator creatorDtoToCreator(CreatorDto creatorDto);
    Creator creatorVideoValueToCreator(CreatorVideoValue creatorVideoValue);
}
