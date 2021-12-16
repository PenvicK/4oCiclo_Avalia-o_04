package br.com.api.avalicao4.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_fornecedores")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFornecedor;

    private String nome;

    @JsonBackReference
    @OneToMany(mappedBy = "fornecedores", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Produto> produtos = new ArrayList<>();

    public Fornecedor(Fornecedor entity){
        idFornecedor = entity.getIdFornecedor();
        nome = entity.getNome();
        produtos = entity.getProdutos().stream().map(produto -> new Produto(produto)).collect(Collectors.toList());
    }
}
