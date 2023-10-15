package com.hiberus.controller;

import com.hiberus.dto.CreatorRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface CreatorController {

    @Operation(summary = "Create a new creator")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Request sent"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    ResponseEntity<Void> createCreator(CreatorRequestDto creatorRequestDto);

    @Operation(summary = "Update the information of a creator")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Request sent"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    ResponseEntity<Void> updateCreator(String creatorId, CreatorRequestDto creatorRequestDto);

    @Operation(summary = "Delete a creator")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Request sent")
    })
    ResponseEntity<Void> deleteCreator(String creatorId);

}
