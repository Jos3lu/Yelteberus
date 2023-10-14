package com.hiberus.mapper;

import com.hiberus.dto.CreatorDto;
import com.hiberus.model.Creator;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreatorMapper {
    CreatorDto creatorToCreatorDto(Creator creator);
    Creator creatorDtoToCreator(CreatorDto creatorDto);
}
