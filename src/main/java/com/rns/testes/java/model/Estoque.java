package com.rns.testes.java.model;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "ESTOQUE")
@Data
public class Estoque extends GenericEntity<EstoquePk> {
    @EmbeddedId
    private EstoquePk id = new EstoquePk();

    @Column(name = "quantidade_produtos")
    private Integer quantidadeDoProduto;

    public Produto getProduto() {
        return this.id.getProduto();
    }

    public void setProduto(Produto produto) {
        this.id.setProduto(produto);
    }

    public Filial getFilial() {
        return this.id.getFilial();
    }

    public void setFilial(Filial filial) { this.id.setFilial(filial); }

    public void entradaNoEstoque(@Min(1) Integer quantidade) {
        this.setQuantidadeDoProduto(this.getQuantidadeDoProduto() + quantidade);
    }

    public void saidaNoEstoque(@Positive Integer quantidade) {
        final int novaQuantidade = this.getQuantidadeDoProduto() - quantidade;

        if (novaQuantidade < 0) {
            throw new IllegalArgumentException
                    ("Não há disponibilidade no estoque de "
                            + quantidade + " itens do produto " + id.getProduto().getNome() + "."
                            + "Temos disponível apenas " + this.quantidadeDoProduto + "itens");
        }
        this.setQuantidadeDoProduto(novaQuantidade);
    }
}
