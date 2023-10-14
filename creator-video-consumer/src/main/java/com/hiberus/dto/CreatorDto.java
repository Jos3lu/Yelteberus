package com.hiberus.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreatorDto {
    private String name;
    private String surname;
}
