package br.com.api.avalicao4.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_tipo_produtos")
public class TipoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoProduto;

    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "tipoProdutos", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Produto> produtos = new ArrayList<>();

    public TipoProduto(TipoProduto entity) {
        idTipoProduto = entity.getIdTipoProduto();
        nome = entity.getNome();
        produtos = entity.getProdutos().stream().map(produto -> new Produto(produto)).collect(Collectors.toList());
    }
}
