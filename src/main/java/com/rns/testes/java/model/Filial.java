package com.rns.testes.java.model;

import com.rns.testes.java.model.enums.EnumTipoFilial;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "FILIAL")
@SequenceGenerator(name = "FILIAL_SEQ", sequenceName = "FILIAL_SEQ", allocationSize = 1)
@Data
public class Filial extends GenericEntity<Long> {

    @Id
    @GeneratedValue(generator = "FILIAL_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String razaoSocial;

    @CNPJ
    @Column
    private String cnpj;

    @Column
    private String endereco;

    @Column
    private EnumTipoFilial tipoFilial;

    @OneToMany(mappedBy = "id.filial", cascade = CascadeType.ALL)
    private Set<Estoque> produtosNoEstoque = new LinkedHashSet<>();

    private Estoque capturaEstoqueAtual(Estoque estoque) {
        return this.produtosNoEstoque.stream()
                .filter(estoque::equals)
                .findAny()
                .orElse(null);
    }

    public void adicionaNoEstoque(Estoque estoque) {
        Estoque estoqueAtual = capturaEstoqueAtual(estoque);

        if(estoqueAtual != null) {
            estoqueAtual.entradaNoEstoque(estoque.getQuantidadeDeProdutos());
        }else {
            this.produtosNoEstoque.add(estoque);
        }
    }

    public void retiraDoEstoque(Estoque estoque) {
        Estoque estoqueAtual = capturaEstoqueAtual(estoque);

        if(estoqueAtual != null) {
            estoqueAtual.saidaNoEstoque(estoque.getQuantidadeDeProdutos());
        }else {
            throw new IllegalArgumentException(String.format("O produto %s não existe no Estoque.",
                    estoque.getProduto().getNome()));
        }
    }

}
