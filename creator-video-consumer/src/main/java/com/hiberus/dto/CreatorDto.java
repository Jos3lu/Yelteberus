package com.hiberus.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreatorDto {
    private String creatorIdentifier;
    private String name;
    private String surname;
    private List<VideoDto> videos;
}
