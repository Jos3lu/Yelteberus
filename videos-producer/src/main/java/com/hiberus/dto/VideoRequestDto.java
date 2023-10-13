package com.hiberus.dto;

import com.hiberus.videosProducer.avro.Category;
import com.hiberus.videosProducer.avro.Format;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoRequestDto {
    private String identifier;
    private String title;
    private String duration;
    private LocalDate uploadDate;
    private Format format;
    protected List<Category> categories;
    private String description;
    private String creatorIdentifier;
}
