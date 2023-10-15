package com.hiberus.service;

import com.hiberus.dto.CreatorRequestDto;
import com.hiberus.dto.CreatorResponseDto;
import com.hiberus.exception.CreatorNotFoundException;
import com.hiberus.exception.CreatorNotValidException;
import com.hiberus.model.Creator;

public interface CreatorService {

    /**
     * Create a new creator
     *
     * @param creator Creator
     * @throws CreatorNotValidException Creator not valid
     */
    void createCreator(Creator creator) throws CreatorNotValidException;

    /**
     * Get creator by identifier
     *
     * @param creatorId Creator identifier
     * @return Creator
     * @throws CreatorNotFoundException Creator not found
     */
    CreatorResponseDto getCreator(String creatorId) throws CreatorNotFoundException;

    /**
     * Update information of creator
     *
     * @param creator Creator
     */
    void updateCreator(String creatorId, Creator creator) throws CreatorNotFoundException, CreatorNotValidException;

    /**
     * Delete creator
     *
     * @param creatorId Creator identifier
     */
    void deleteCreator(String creatorId);

}
