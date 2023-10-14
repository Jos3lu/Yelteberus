package com.hiberus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "video")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
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
