package br.com.api.avalicao4.controllers;


import br.com.api.avalicao4.models.TipoProduto;
import br.com.api.avalicao4.services.TipoProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tipo-produtos")
public class TipoProdutoController {

    @Autowired
    private TipoProdutoService service;

    @GetMapping
    public ResponseEntity<List<TipoProduto>> findAll() {
        List<TipoProduto> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<TipoProduto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping(path = "/nome/{nome}")
    public ResponseEntity<TipoProduto> findByNome(@PathVariable String nome) {
        return ResponseEntity.ok(service.findByNome(nome));
    }


    @PostMapping
    public ResponseEntity<TipoProduto> save(@RequestBody TipoProduto tipoProduto) throws Exception {
        return new ResponseEntity<>(service.insert(tipoProduto), HttpStatus.CREATED);
    }


    @PutMapping(path = "/{id}")
    public ResponseEntity<TipoProduto> update(@PathVariable Long id, @RequestBody TipoProduto update){
        return ResponseEntity.ok(service.update(id, update));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.ok("Tipo produto " + id + " deletado!");
    }

}
