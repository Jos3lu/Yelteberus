package com.hiberus.controller.impl;

import com.hiberus.controller.CreatorController;
import com.hiberus.dto.CreatorResponseDto;
import com.hiberus.dto.VideoResponseDto;
import com.hiberus.exception.CreatorNotFoundException;
import com.hiberus.exception.VideoNotFoundException;
import com.hiberus.mapper.CreatorMapper;
import com.hiberus.mapper.VideoMapper;
import com.hiberus.service.CreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/creators")
public class CreatorControllerImpl implements CreatorController {

    @Autowired
    private CreatorService creatorService;

    @Autowired
    private CreatorMapper creatorMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Override
    @GetMapping
    public ResponseEntity<List<CreatorResponseDto>> getCreators() {
        List<CreatorResponseDto> creatorResponseDtos = creatorMapper
                .creatorListToCreatorResponseDtoList(creatorService.getCreators());
        return ResponseEntity.ok(creatorResponseDtos);
    }

    @Override
    @GetMapping("/{creatorId}")
    public ResponseEntity<CreatorResponseDto> getCreator(@PathVariable String creatorId) {
        try {
            return ResponseEntity.ok(creatorMapper.creatorToCreatorResponseDto(creatorService
                    .getCreator(creatorId)));
        } catch (CreatorNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @GetMapping("/{creatorId}/videos/{videoId}")
    public ResponseEntity<VideoResponseDto> getVideoCreator(@PathVariable String creatorId, @PathVariable String videoId) {
        try {
            return ResponseEntity.ok(videoMapper.videoToVideoResponseDto(creatorService.
                    getVideoCreator(creatorId, videoId)));
        } catch (CreatorNotFoundException | VideoNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
