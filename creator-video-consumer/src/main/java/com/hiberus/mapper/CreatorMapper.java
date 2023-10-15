package com.hiberus.mapper;

import com.hiberus.creatorVideoConsumer.avro.CreatorVideoValue;
import com.hiberus.dto.CreatorResponseDto;
import com.hiberus.model.Creator;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CreatorMapper {
    CreatorResponseDto creatorToCreatorResponseDto(Creator creator);
    List<CreatorResponseDto> creatorListToCreatorResponseDtoList(List<Creator> creators);
    Creator creatorVideoValueToCreator(CreatorVideoValue creatorVideoValue);
}
