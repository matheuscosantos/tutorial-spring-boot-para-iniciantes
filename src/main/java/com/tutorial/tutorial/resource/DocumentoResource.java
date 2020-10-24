package com.tutorial.tutorial.resource;

import com.tutorial.tutorial.model.Documento;
import com.tutorial.tutorial.repository.DocumentoRepository;
import com.tutorial.tutorial.resource.dto.DocumentoDTO;
import com.tutorial.tutorial.resource.form.DocumentoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
class DocumentoResource {

    //    Injeção de dependência
    @Autowired
    DocumentoRepository documentoRepository;

    @PostMapping("/documento")
    public Documento salva(@RequestBody DocumentoForm documentoForm) {
        Documento documento = documentoForm.toModel(documentoForm);
        return documentoRepository.save(documento);
    }

    @GetMapping("/documento/{id}")
    public ResponseEntity<?> buscaPorId(@PathVariable(value = "id") Integer id) {
        try{
            Optional<Documento> documentoEncontrado = documentoRepository.findById(id);
            if(documentoEncontrado.isPresent()){
                return new ResponseEntity<>(new DocumentoDTO(documentoEncontrado.get()), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/documento/{id}")
    public void apagaPorId(@PathVariable(value = "id") Integer id) {
        Optional<Documento> documento = documentoRepository.findById(id);
        if (documento.isPresent()) {
            documentoRepository.delete(documento.get());
        }
    }

    @PutMapping("/documento/{id}")
    public Documento atualiza(@PathVariable(value = "id") Integer id,
                              @RequestBody Documento documento) {
        Optional<Documento> documentoAntigo = documentoRepository.findById(id);
        if (documentoAntigo.isPresent()) {
            documentoAntigo.get().setTitulo(documento.getTitulo());
            documentoAntigo.get().setAutor(documento.getAutor());
            return documentoRepository.save(documentoAntigo.get());
        }
        return documentoAntigo.get();
    }

    @GetMapping("/documentos")
    public List<Documento> lista() {
        return documentoRepository.findAll();
    }
}