package com.hiberus.service.impl;

import com.hiberus.dto.ChannelDto;
import com.hiberus.model.Channel;
import com.hiberus.repository.ChannelRepository;
import com.hiberus.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelRepository channelRepository;

    @Override
    public List<Channel> getChannels() {
        return channelRepository.findAll();
    }

}
