package com.hiberus.service;

import com.hiberus.dto.VideoResponseDto;
import com.hiberus.exception.VideoNotFoundException;
import com.hiberus.exception.VideoNotValidException;
import com.hiberus.model.Video;
import org.springframework.web.bind.annotation.PathVariable;

public interface VideoService {

    /**
     * Create a new video
     *
     * @param video Video
     * @throws VideoNotValidException Video not valid
     */
    void createVideo(Video video) throws VideoNotValidException;

    /**
     * Get video by identifier
     *
     * @param creatorId Creator identifier
     * @param videoId Video identifier
     * @return Video
     * @throws VideoNotFoundException Video not found
     */
    VideoResponseDto getVideoCreator(String creatorId, String videoId) throws VideoNotFoundException;

    /**
     * Update a video
     *
     * @param creatorId Creator identifier
     * @param videoId Video identifier
     * @param video Video
     * @throws VideoNotValidException Video not valid
     * @throws VideoNotFoundException Video not found
     */
    void updateVideo(String creatorId, String videoId, Video video) throws VideoNotValidException, VideoNotFoundException;

}
