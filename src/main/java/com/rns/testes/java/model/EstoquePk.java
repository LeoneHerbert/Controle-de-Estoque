package com.rns.testes.java.model;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
@Data
public class EstoquePk implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "filial_id")
    private Filial filial;
}
