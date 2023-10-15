package com.hiberus.service;

import com.hiberus.model.Creator;

import java.util.List;

public interface CreatorService {

    /**
     * Get all the creators
     *
     * @return List of creators
     */
    List<Creator> getCreators();

}
