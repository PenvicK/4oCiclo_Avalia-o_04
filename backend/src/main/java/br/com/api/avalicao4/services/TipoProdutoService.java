package br.com.api.avalicao4.services;


import br.com.api.avalicao4.exceptions.ProductAlreadyExistsException;
import br.com.api.avalicao4.exceptions.ProductNotFoundException;
import br.com.api.avalicao4.models.TipoProduto;
import br.com.api.avalicao4.repositories.TipoProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoProdutoService {

    @Autowired
    private TipoProdutoRepository repository;

    public List<TipoProduto> findAll() {
        List<TipoProduto> result = repository.findAll();
        return result.stream().map(x -> new TipoProduto(x)).collect(Collectors.toList());
    }

    public TipoProduto insert(TipoProduto tipoProduto){

        Optional<TipoProduto> tipoProdutoVerify = repository.findByNome(tipoProduto.getNome());
        if(tipoProdutoVerify.isPresent()) {
            throw new ProductAlreadyExistsException("Tipo produto já existe!!");
        }

        TipoProduto unit = new TipoProduto();
        unit.setIdTipoProduto(tipoProduto.getIdTipoProduto());
        unit.setNome(tipoProduto.getNome());


        unit = repository.save(unit);

        return unit;
    }

    public TipoProduto findById(Long id) {
        Optional<TipoProduto> result = repository.findById(id);
        return result.orElseThrow(() -> new ProductNotFoundException("Tipo produto não encontrado. Porfavor tente novamente."));
    }

    public TipoProduto findByNome(String nome) {
        Optional<TipoProduto> result = repository.findByNome(nome);
        return result.orElseThrow(() -> new ProductNotFoundException("Tipo produto não encontrado. Porfavor tente novamente."));
    }

    public void deleteById(Long id){
        repository.delete(findById(id));
    }

    public TipoProduto update(Long id, TipoProduto update){
        TipoProduto updated = findById(id);

        updated.setNome(update.getNome());

        repository.save(updated);

        return updated;
    }

}
