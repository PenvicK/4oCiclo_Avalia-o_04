package br.com.api.avalicao4.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduto;

    private String nome;
    private int quantidadeEmEstoque;
    private Double precoVenda;
    private Double precoCompra;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idTipoProduto")
    private TipoProduto tipoProdutos;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idFornecedores")
    private Fornecedor fornecedores;


    public Produto(Produto entity){
        idProduto = entity.getIdProduto();
        nome = entity.getNome();
        quantidadeEmEstoque = entity.getQuantidadeEmEstoque();
        precoVenda = entity.getPrecoVenda();
        precoCompra = entity.getPrecoCompra();
        tipoProdutos = entity.getTipoProdutos();
        fornecedores = entity.getFornecedores();
    }

}
