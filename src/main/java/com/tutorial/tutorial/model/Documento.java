package com.tutorial.tutorial.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="TB_PRODUTO")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Documento {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String titulo;
    private String autor;

    public Documento(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }
}


