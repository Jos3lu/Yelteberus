package com.hiberus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "creator")
public class Creator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String creatorIdentifier;
    private String name;
    private String surname;

}
