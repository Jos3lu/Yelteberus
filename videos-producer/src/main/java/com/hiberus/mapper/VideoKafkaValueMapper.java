package com.hiberus.mapper;

import com.hiberus.model.Video;
import com.hiberus.videosProducer.avro.VideoValue;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VideoKafkaValueMapper {
    VideoValue videoToVideoValue(Video video);
}
