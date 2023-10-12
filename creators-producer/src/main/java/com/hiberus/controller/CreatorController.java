package com.hiberus.controller;

import com.hiberus.dto.CreatorRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface CreatorController {

    @Operation(summary = "Create a new creator")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Request sent")
    })
    ResponseEntity<Void> createCreator(CreatorRequestDto creatorRequestDto);

}
