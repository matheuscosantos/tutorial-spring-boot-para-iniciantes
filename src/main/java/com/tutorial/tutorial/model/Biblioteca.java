package com.tutorial.tutorial.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Biblioteca {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "biblioteca_id")
    private Integer id;
    private String nome;

    @OneToMany
    Set<Estante> estantes = new HashSet<>();

}
