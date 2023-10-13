package com.hiberus.service;

import com.hiberus.exception.VideoNotValidException;
import com.hiberus.model.Video;

public interface VideoService {

    /**
     * Create a new video
     *
     * @param video Video
     * @throws VideoNotValidException Video not valid
     */
    void createVideo(Video video) throws VideoNotValidException;

}
