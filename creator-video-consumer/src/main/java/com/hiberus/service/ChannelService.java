package com.hiberus.service;

import com.hiberus.dto.ChannelDto;
import com.hiberus.model.Channel;

import java.util.List;

public interface ChannelService {

    /**
     * Get all the channels
     *
     * @return List of channels
     */
    List<Channel> getChannels();

}
