package com.hiberus.service;

import com.hiberus.videosProducer.avro.VideoKey;
import com.hiberus.videosProducer.avro.VideoValue;

public interface VideoPcsService {

    /**
     * Process incoming videos
     *
     * @param key Video key
     * @param value Video value
     */
    void process(VideoKey key, VideoValue value);
}
