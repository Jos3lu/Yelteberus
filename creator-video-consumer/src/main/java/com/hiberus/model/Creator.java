package com.hiberus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "creator")
public class Creator {

    @Id
    private String creatorIdentifier;
    private String name;
    private String surname;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Video> videos;

}
