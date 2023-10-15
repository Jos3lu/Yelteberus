package com.hiberus.client;

import com.hiberus.dto.CreatorResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8087", name = "creator-video-consumer")
public interface ClientCreatorConsumer {

    @GetMapping(value = "/api/creators/{creatorId}")
    ResponseEntity<CreatorResponseDto> getCreator(@PathVariable String creatorId);

}
