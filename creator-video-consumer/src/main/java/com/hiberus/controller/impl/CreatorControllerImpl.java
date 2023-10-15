package com.hiberus.controller.impl;

import com.hiberus.controller.CreatorController;
import com.hiberus.dto.CreatorDto;
import com.hiberus.mapper.CreatorMapper;
import com.hiberus.service.CreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/channels")
public class CreatorControllerImpl implements CreatorController {

    @Autowired
    private CreatorService creatorService;

    @Autowired
    private CreatorMapper creatorMapper;

    @Override
    @GetMapping
    public ResponseEntity<List<CreatorDto>> getCreators() {
        List<CreatorDto> creatorDtos = creatorService.getCreators().stream()
                .map(creatorMapper::creatorToCreatorDto)
                .toList();
        return ResponseEntity.ok(creatorDtos);
    }
}
