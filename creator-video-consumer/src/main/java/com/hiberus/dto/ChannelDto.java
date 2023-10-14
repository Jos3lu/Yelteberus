package com.hiberus.dto;

import com.hiberus.model.Creator;
import com.hiberus.model.Video;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ChannelDto {
    private String creatorIdentifier;
    private Creator creator;
    private List<Video> videos;
}
