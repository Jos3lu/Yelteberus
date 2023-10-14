package com.hiberus.mapper;

import com.hiberus.videoAggregator.avro.Video;
import com.hiberus.videoPcs.avro.VideoPcsValue;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VideoKafkaMapper {
    Video videoPcsValueToVideo(VideoPcsValue videoPcsValue);
}
