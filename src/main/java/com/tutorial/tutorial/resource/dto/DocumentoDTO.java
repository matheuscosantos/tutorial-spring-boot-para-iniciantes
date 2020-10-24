package com.tutorial.tutorial.resource.dto;

import com.tutorial.tutorial.model.Documento;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DocumentoDTO {
    private String titulo;

    public DocumentoDTO(Documento documento) {
        this.titulo = documento.getTitulo();
    }
}
