package com.hiberus.controller.impl;

import com.hiberus.controller.ChannelController;
import com.hiberus.dto.ChannelDto;
import com.hiberus.mapper.ChannelMapper;
import com.hiberus.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/channels")
public class ChannelControllerImpl implements ChannelController {

    @Autowired
    private ChannelService channelService;

    @Autowired
    private ChannelMapper channelMapper;

    @Override
    @GetMapping
    public ResponseEntity<List<ChannelDto>> getChannels() {
        List<ChannelDto> channelDtos = channelService.getChannels().stream()
                .map(channelMapper::channelToChannelDto)
                .toList();
        return ResponseEntity.ok(channelDtos);
    }
}
