package com.tutorial.tutorial.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "livro_id")
    private Integer livroId;

    @ManyToOne
    @JoinColumn(name = "estante_id")
    private Estante estante;

    @ManyToMany
    @JoinTable(name = "livro_autor",
            joinColumns = { @JoinColumn(name = "livro_fk") },
            inverseJoinColumns = { @JoinColumn(name = "autor_fk") })
    private Set<Autor> authors = new HashSet<Autor>();
}


