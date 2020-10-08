package com.rns.testes.java.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "ESTOQUE")
@Data
public class Estoque {
    @EmbeddedId
    private EstoquePk id = new EstoquePk();

    @NotNull
    @Min(0)
    @Column(name = "quantidade_produtos")
    private Integer quantidadeProdutos;

    public void adicionaNoEstoque(@Min(1) Integer quantidade) {
        this.setQuantidadeProdutos(this.getQuantidadeProdutos() + quantidade);
    }

    public void baixaNoEstoque(@Positive Integer quantidade)  {
        final int novaQuantidade = this.getQuantidadeProdutos() - quantidade;

        if (novaQuantidade < 0) {
            throw new IllegalArgumentException
                    ("Não há disponibilidade no estoque de "
                            + quantidade + " itens do produto " + id.getProduto().getNome() + "."
                            + "Temos disponível apenas " + this.quantidadeProdutos + "itens" );
        }
        this.setQuantidadeProdutos(novaQuantidade );
    }
}
