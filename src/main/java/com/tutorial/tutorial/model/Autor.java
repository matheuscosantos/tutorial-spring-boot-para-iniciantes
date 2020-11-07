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
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer autor_id;
    private String nome;

    @ManyToMany(mappedBy="authors")
    private Set<Livro> livros = new HashSet<>();
}
