package com.hiberus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "channel")
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String creatorIdentifier;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Creator creator;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Video> videos;

}
