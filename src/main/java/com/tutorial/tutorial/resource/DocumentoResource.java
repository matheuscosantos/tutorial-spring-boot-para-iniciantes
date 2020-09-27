package com.tutorial.tutorial.resource;

import com.tutorial.tutorial.model.Documento;
import com.tutorial.tutorial.repository.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api")
class ProdutoResource {

//    Injeção de dependência
    @Autowired
    DocumentoRepository documentoRepository;

    @PostMapping("/documento")
    public Documento salva(@RequestBody Documento documento) {
        return documentoRepository.save(documento);
    }

    @GetMapping("/documento/{id}")
    public Optional<Documento> buscaPorId(@PathVariable(value="id") Integer id){
        return documentoRepository.findById(id);
    }

    @DeleteMapping("/documento/{id}")
    public void apagaPorId(@PathVariable(value="id") Integer id){
        Optional<Documento> documento = documentoRepository.findById(id);
        if (documento.isPresent()){
            documentoRepository.delete(documento.get());
        }
    }

    @PutMapping("/documento/{id}")
    public Documento atualiza(@PathVariable(value="id") Integer id,
                              @RequestBody Documento documento){
        Optional<Documento> documentoAntigo = documentoRepository.findById(id);
        if (documentoAntigo.isPresent()){
            documentoAntigo.get().setTitulo(documento.getTitulo());
            documentoAntigo.get().setAutor(documento.getAutor());
            return documentoRepository.save(documentoAntigo.get());
        }
        return documentoAntigo.get();
    }

    @GetMapping("/documentos")
    public List<Documento> lista(){
        return documentoRepository.findAll();
    }
}