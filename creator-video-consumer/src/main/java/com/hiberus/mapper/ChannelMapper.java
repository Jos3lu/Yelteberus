package com.hiberus.mapper;

import com.hiberus.dto.ChannelDto;
import com.hiberus.model.Channel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChannelMapper {
    ChannelDto channelToChannelDto(Channel channel);
    Channel channelDtoToChannel(ChannelDto channelDto);
}
