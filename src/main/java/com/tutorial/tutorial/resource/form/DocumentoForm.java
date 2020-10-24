package com.tutorial.tutorial.resource.form;

import com.tutorial.tutorial.model.Documento;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DocumentoForm {
    private String titulo;

    public Documento toModel(DocumentoForm documentoForm){
        Documento documento = new Documento();
        documento.setTitulo(documentoForm.getTitulo());
        return documento;
    }

}
