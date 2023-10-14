package com.hiberus.controller.impl;

import com.hiberus.controller.VideoController;
import com.hiberus.dto.VideoRequestDto;
import com.hiberus.exception.VideoNotValidException;
import com.hiberus.mapper.VideoMapper;
import com.hiberus.model.Video;
import com.hiberus.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/videos")
public class VideoControllerImpl implements VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private VideoMapper videoMapper;

    @Override
    @PostMapping
    public ResponseEntity<Void> createVideo(@RequestBody VideoRequestDto videoRequestDto) {
        try {
            Video video = videoMapper.videoRequestDtoToVideo(videoRequestDto);
            videoService.createVideo(video);
            return ResponseEntity.accepted().build();
        } catch (VideoNotValidException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
