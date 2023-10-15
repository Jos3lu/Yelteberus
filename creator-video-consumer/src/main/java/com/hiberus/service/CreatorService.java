package com.hiberus.service;

import com.hiberus.exception.CreatorNotFoundException;
import com.hiberus.exception.VideoNotFoundException;
import com.hiberus.model.Creator;
import com.hiberus.model.Video;

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

    /**
     * Get video associated to a creator
     *
     * @param creatorId Creator ID
     * @param videoId Video ID
     * @return Video
     */
    Video getVideoCreator(String creatorId, String videoId) throws CreatorNotFoundException, VideoNotFoundException;
}
