package br.com.api.avalicao4.repositories;

import br.com.api.avalicao4.models.Fornecedor;
import br.com.api.avalicao4.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    Optional<Fornecedor> findByNome(String nome);
}
