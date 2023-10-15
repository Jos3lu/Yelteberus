package com.hiberus.mapper;

import com.hiberus.dto.VideoDto;
import com.hiberus.model.Video;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VideoMapper {
    VideoDto videoToVideoDto(Video video);
    Video videoDtoToVideo(VideoDto videoDto);
    Video videoValueToVideo(com.hiberus.creatorVideoConsumer.avro.Video video);
}
