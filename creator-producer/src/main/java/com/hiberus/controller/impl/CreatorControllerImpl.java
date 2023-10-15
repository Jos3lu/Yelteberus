package com.hiberus.controller.impl;

import com.hiberus.controller.CreatorController;
import com.hiberus.exception.CreatorNotFoundException;
import com.hiberus.exception.CreatorNotValidException;
import com.hiberus.model.Creator;
import com.hiberus.service.CreatorService;
import com.hiberus.dto.CreatorRequestDto;
import com.hiberus.mapper.CreatorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/creators")
public class CreatorControllerImpl implements CreatorController {

    @Autowired
    private CreatorService creatorService;

    @Autowired
    private CreatorMapper creatorMapper;

    @Override
    @PostMapping
    public ResponseEntity<Void> createCreator(@RequestBody CreatorRequestDto creatorRequestDto) {
        try {
            Creator creator = creatorMapper.creatorRequestDtoToCreator(creatorRequestDto);
            creatorService.createCreator(creator);
            return ResponseEntity.accepted().build();
        } catch (CreatorNotValidException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    @PutMapping("/{creatorId}")
    public ResponseEntity<Void> updateCreator(@PathVariable String creatorId, @RequestBody CreatorRequestDto creatorRequestDto) {
        try {
            Creator creator = creatorMapper.creatorRequestDtoToCreator(creatorRequestDto);
            creatorService.updateCreator(creatorId, creator);
            return ResponseEntity.accepted().build();
        } catch (CreatorNotValidException e) {
            return ResponseEntity.badRequest().build();
        } catch (CreatorNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @DeleteMapping("/{creatorId}")
    public ResponseEntity<Void> deleteCreator(@PathVariable String creatorId) {
        creatorService.deleteCreator(creatorId);
        return ResponseEntity.accepted().build();
    }

}
