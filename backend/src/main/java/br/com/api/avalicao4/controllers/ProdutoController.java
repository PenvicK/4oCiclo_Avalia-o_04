package br.com.api.avalicao4.controllers;


import br.com.api.avalicao4.models.Produto;
import br.com.api.avalicao4.repositories.ProdutoRepository;
import br.com.api.avalicao4.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public ResponseEntity<List<Produto>> findAll() {
        List<Produto> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping(path = "/nome/{nome}")
    public ResponseEntity<Produto> findByNome(@PathVariable String nome) {
        return ResponseEntity.ok(service.findByNome(nome));
    }

    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody Produto produto) throws Exception {
        return new ResponseEntity<>(service.insert(produto), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody Produto update){
        return ResponseEntity.ok(service.update(id, update));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.ok("Produto " + id + " deletado!");
    }

}
