package com.hiberus.mapper;

import com.hiberus.dto.CreatorRequestDto;
import com.hiberus.model.Creator;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreatorMapper {
    Creator creatorRequestDtoToCreator(CreatorRequestDto creatorRequestDto);
}
