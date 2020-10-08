package com.rns.testes.java.model;

import lombok.Data;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "PRODUTO")
@Data
public class Produto extends GenericEntity<String>{

    @Id
    private String id;

    @Column
    private String nome;

    @OneToMany(mappedBy = "id.produto", cascade = CascadeType.ALL)
    private Set<Estoque> estoque = new LinkedHashSet<>();

}
