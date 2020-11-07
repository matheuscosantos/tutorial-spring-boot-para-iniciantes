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
public class Estante {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "estante_id")
    private Integer estanteId;
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "biblioteca_id")
    private Biblioteca biblioteca;

    @OneToMany
    Set<Livro> livros = new HashSet<>();

}
