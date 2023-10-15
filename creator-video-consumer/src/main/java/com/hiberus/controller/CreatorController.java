package com.hiberus.controller;

import com.hiberus.dto.CreatorResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CreatorController {

    @Operation(summary = "Get creators")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully recovered")
    })
    ResponseEntity<List<CreatorResponseDto>> getCreators();

    @Operation(summary = "Get creator by identifier")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully recovered")
    })
    ResponseEntity<CreatorResponseDto> getCreator(String creatorId);

}
