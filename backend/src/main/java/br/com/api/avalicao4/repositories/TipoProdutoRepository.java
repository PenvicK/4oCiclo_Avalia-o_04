package br.com.api.avalicao4.repositories;

import br.com.api.avalicao4.models.Produto;
import br.com.api.avalicao4.models.TipoProduto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoProdutoRepository extends JpaRepository<TipoProduto, Long> {
    Optional<TipoProduto> findByNome(String nome);
}
