package com.hiberus.controller;

import com.hiberus.dto.VideoRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface VideoController {

    @Operation(summary = "Create a new video")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Request sent"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    ResponseEntity<Void> createVideo(VideoRequestDto videoRequestDto);

    @Operation(summary = "Update the information of a video")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Request sent"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    ResponseEntity<Void> updateVideo(String creatorId, String videoId, VideoRequestDto videoRequestDto);

}
