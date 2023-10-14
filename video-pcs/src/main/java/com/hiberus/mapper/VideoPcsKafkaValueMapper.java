package com.hiberus.mapper;

import com.hiberus.videoPcs.avro.VideoPcsValue;
import com.hiberus.videoProducer.avro.VideoValue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VideoPcsKafkaValueMapper {
    @Mapping(ignore = true, target = "views")
    @Mapping(ignore = true, target = "resolution")
    @Mapping(ignore = true, target = "privacy")
    VideoPcsValue videoValueToVideoPcsvalue(VideoValue videoValue);
}
