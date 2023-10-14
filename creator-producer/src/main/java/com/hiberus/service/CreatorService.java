package com.hiberus.service;

import com.hiberus.dto.CreatorRequestDto;
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

}
