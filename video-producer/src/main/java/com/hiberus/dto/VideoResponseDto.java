package com.hiberus.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class VideoResponseDto {
    private String videoIdentifier;
    private String title;
    private String duration;
    private LocalDate uploadDate;
    private String format;
    private List<String> categories;
    private String description;
    private Integer views;
    private String resolution;
    private String privacy;
}
