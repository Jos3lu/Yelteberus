package com.hiberus.controller;

import com.hiberus.dto.ChannelDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ChannelController {

    @Operation(summary = "Get channels")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully recovered")
    })
    ResponseEntity<List<ChannelDto>> getChannels();

}
