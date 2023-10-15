package com.hiberus.service;

import com.hiberus.exception.CreatorNotFoundException;
import com.hiberus.model.Creator;

import java.util.List;

public interface CreatorService {

    /**
     * Get all the creators
     *
     * @return List of creators
     */
    List<Creator> getCreators();

    /**
     * Save creator (and videos) in database
     *
     * @param creator Creator (with videos)
     */
    void saveCreator(Creator creator);

    /**
     * Get creator by ID
     *
     * @param creatorId Creator ID
     * @return Creator
     */
    Creator getCreator(String creatorId) throws CreatorNotFoundException;
}
