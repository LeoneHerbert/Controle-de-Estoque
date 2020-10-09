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
    private Integer quantidadeDeProdutos;

    public Produto getProduto() {
        return this.id.getProduto();
    }

    public void setProduto(Produto produto) {
        id.setProduto(produto);
    }

    public Filial getFilial() {
        return id.getFilial();
    }

    public void setFilial(Filial filial) {
        id.setFilial(filial);
    }

    public void entradaNoEstoque(@Min(1) Integer quantidade) {
        this.setQuantidadeDeProdutos(this.getQuantidadeDeProdutos() + quantidade);
    }

    public void saidaNoEstoque(@Positive Integer quantidade) {
        final int novaQuantidade = this.getQuantidadeDeProdutos() - quantidade;

        if (novaQuantidade < 0) {
            throw new IllegalArgumentException
                    ("Não há disponibilidade no estoque de "
                            + quantidade + " itens do produto " + id.getProduto().getNome() + "."
                            + "Temos disponível apenas " + this.quantidadeDeProdutos + "itens");
        }
        this.setQuantidadeDeProdutos(novaQuantidade);
    }
}
