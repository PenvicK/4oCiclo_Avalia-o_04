package br.com.api.avalicao4.services;

import br.com.api.avalicao4.exceptions.ProductAlreadyExistsException;
import br.com.api.avalicao4.models.Produto;
import br.com.api.avalicao4.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<Produto> findAll() {
        List<Produto> result = repository.findAll();
        return result.stream().map(x -> new Produto(x)).collect(Collectors.toList());
    }

    public Produto insert(Produto produto){

        Optional<Produto> produtoVerify = repository.findByNome(produto.getNome());
        if(produtoVerify.isPresent()) {
            throw new ProductAlreadyExistsException("Produto já existe!!");
        }

        Produto unit = new Produto();
        unit.setIdProduto(produto.getIdProduto());
        unit.setNome(produto.getNome());
        unit.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque());
        unit.setPrecoCompra(produto.getPrecoCompra());
        unit.setPrecoVenda(produto.getPrecoVenda());
        unit.setTipoProdutos(produto.getTipoProdutos());
        unit.setFornecedores(produto.getFornecedores());

        unit = repository.save(unit);

        return unit;
    }

    public Produto findById(Long id) {
        Optional<Produto> result = repository.findById(id);
        return result.orElseThrow(() -> new ProductAlreadyExistsException("Produto não encontrado. Porfavor tente novamente."));
    }

    public Produto findByNome(String nome) {
        Optional<Produto> result = repository.findByNome(nome);
        return result.orElseThrow(() -> new ProductAlreadyExistsException("Produto não encontrado. Porfavor tente novamente."));
    }

    public void deleteById(Long id){
        repository.delete(findById(id));
    }

    public Produto edit(Long id, Produto update){
        Produto updated = findById(id);

        updated.setNome(update.getNome());
        updated.setQuantidadeEmEstoque(update.getQuantidadeEmEstoque());
        updated.setPrecoCompra(update.getPrecoCompra());
        updated.setPrecoVenda(update.getPrecoVenda());
        updated.setTipoProdutos(update.getTipoProdutos());
        updated.setFornecedores(update.getFornecedores());

        repository.save(updated);

        return updated;
    }

}
