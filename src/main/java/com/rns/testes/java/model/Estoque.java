package com.rns.testes.java.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
}
