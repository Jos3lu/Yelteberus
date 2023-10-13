package com.hiberus.mapper;

import com.hiberus.dto.VideoRequestDto;
import com.hiberus.model.Video;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VideoMapper {
    Video videoRequestDtoToVideo(VideoRequestDto videoRequestDto);
}
