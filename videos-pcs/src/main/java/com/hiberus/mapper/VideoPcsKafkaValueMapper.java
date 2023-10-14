package com.hiberus.mapper;

import com.hiberus.videosPcs.avro.VideoPcsValue;
import com.hiberus.videosProducer.avro.VideoValue;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VideoPcsKafkaValueMapper {
    VideoPcsValue videoValueToVideoPcsvalue(VideoValue videoValue);
}
