package com.hiberus.service.impl;

import com.hiberus.exception.CreatorNotFoundException;
import com.hiberus.exception.VideoNotFoundException;
import com.hiberus.model.Creator;
import com.hiberus.model.Video;
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
        log.info("[CreatorVideoConsumer] Sending all the saved creators");
        return creatorRepository.findAll();
    }

    @Override
    public void saveCreator(Creator creator) {
        log.info("[CreatorVideoConsumer] Saving creator");
        creatorRepository.save(creator);
    }

    @Override
    public Creator getCreator(String creatorId) throws CreatorNotFoundException {
        log.info("[CreatorVideoConsumer] Searching creator {}", creatorId);
        return creatorRepository.findById(creatorId)
                .orElseThrow(() -> {
                    log.error("[CreatorVideoConsumer] Creator {} could not be found", creatorId);
                    return new CreatorNotFoundException(creatorId);
                });
    }

    @Override
    public Video getVideoCreator(String creatorId, String videoId) throws CreatorNotFoundException, VideoNotFoundException {
        log.info("[CreatorVideoConsumer] Searching video {} of creator {}", videoId, creatorId);
        Creator creator = getCreator(creatorId);

        return creator.getVideos().stream()
                .filter(videoCreator -> videoCreator.getVideoIdentifier().equals(videoId))
                .findFirst()
                .orElseThrow(() -> {
                    log.error("[CreatorVideoConsumer] Video {} of creator {} could not be found", videoId, creatorId);
                    return new VideoNotFoundException(videoId);
                });
    }

}
