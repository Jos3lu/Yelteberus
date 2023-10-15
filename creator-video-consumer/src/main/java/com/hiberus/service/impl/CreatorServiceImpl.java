package com.hiberus.service.impl;

import com.hiberus.model.Creator;
import com.hiberus.repository.CreatorRepository;
import com.hiberus.service.CreatorService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CreatorServiceImpl implements CreatorService {

    @Autowired
    private CreatorRepository creatorRepository;

    @Override
    public List<Creator> getCreators() {
        return creatorRepository.findAll();
    }

}
