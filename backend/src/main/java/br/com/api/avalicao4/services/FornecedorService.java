package br.com.api.avalicao4.services;

import br.com.api.avalicao4.exceptions.ProductAlreadyExistsException;
import br.com.api.avalicao4.exceptions.ProductNotFoundException;
import br.com.api.avalicao4.models.Fornecedor;
import br.com.api.avalicao4.repositories.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository repository;

    public List<Fornecedor> findAll() {
        List<Fornecedor> result = repository.findAll();
        return result.stream().map(x -> new Fornecedor(x)).collect(Collectors.toList());
    }

    public Fornecedor insert(Fornecedor fornecedor){

        Optional<Fornecedor> fornecedorVerify = repository.findByNome(fornecedor.getNome());
        if(fornecedorVerify.isPresent()) {
            throw new ProductAlreadyExistsException("Fornecedor já existe!!");
        }

        Fornecedor unit = new Fornecedor();
        unit.setIdFornecedor(fornecedor.getIdFornecedor());
        unit.setNome(fornecedor.getNome());

        unit = repository.save(unit);

        return unit;
    }

    public Fornecedor findById(Long id) {
        Optional<Fornecedor> result = repository.findById(id);
        return result.orElseThrow(() -> new ProductNotFoundException("Fornecedor não encontrado. Porfavor tente novamente."));
    }

    public Fornecedor findByNome(String nome) {
        Optional<Fornecedor> result = repository.findByNome(nome);
        return result.orElseThrow(() -> new ProductNotFoundException("Fornecedor não encontrado. Porfavor tente novamente."));
    }

    public void deleteById(Long id){
        repository.delete(findById(id));
    }

    public Fornecedor update(Long id, Fornecedor update){
        Fornecedor updated = findById(id);

        updated.setNome(update.getNome());

        repository.save(updated);

        return updated;
    }

}
