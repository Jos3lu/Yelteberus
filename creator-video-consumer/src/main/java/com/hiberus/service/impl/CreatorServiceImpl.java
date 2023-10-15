package com.hiberus.service.impl;

import com.hiberus.exception.CreatorNotFoundException;
import com.hiberus.model.Creator;
import com.hiberus.repository.CreatorRepository;
import com.hiberus.service.CreatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CreatorServiceImpl implements CreatorService {

    @Autowired
    private CreatorRepository creatorRepository;

    @Override
    public List<Creator> getCreators() {
        log.info("Sending all the saved creators");
        return creatorRepository.findAll();
    }

    @Override
    public void saveCreator(Creator creator) {
        log.info("Saving creator");
        creatorRepository.save(creator);
    }

    @Override
    public Creator getCreator(String creatorId) throws CreatorNotFoundException {
        log.info("Searching creator {}", creatorId);
        return creatorRepository.findById(creatorId)
                .orElseThrow(() -> {
                    log.error("Creator {} could not be found", creatorId);
                    return new CreatorNotFoundException(creatorId);
                });
    }

}
