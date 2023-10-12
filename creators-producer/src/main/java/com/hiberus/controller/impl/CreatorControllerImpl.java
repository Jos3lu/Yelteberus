package com.hiberus.controller.impl;

import com.hiberus.controller.CreatorController;
import com.hiberus.exception.CreatorNotValidException;
import com.hiberus.model.Creator;
import com.hiberus.service.CreatorService;
import com.hiberus.dto.CreatorRequestDto;
import com.hiberus.mapper.CreatorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/creators")
public class CreatorControllerImpl implements CreatorController {

    @Autowired
    private CreatorService creatorService;

    @Autowired
    private CreatorMapper creatorMapper;

    @Override
    @PostMapping
    public ResponseEntity<Void> createCreator(CreatorRequestDto creatorRequestDto) {
        try {
            Creator creator = creatorMapper.creatorRequestDtoToCreator(creatorRequestDto);
            creatorService.createCreator(creator);
            return ResponseEntity.accepted().build();
        } catch (CreatorNotValidException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
