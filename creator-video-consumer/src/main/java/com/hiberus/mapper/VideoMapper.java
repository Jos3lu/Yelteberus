package com.hiberus.mapper;

import com.hiberus.dto.VideoResponseDto;
import com.hiberus.model.Video;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VideoMapper {
    VideoResponseDto videoToVideoResponseDto(Video video);
}
