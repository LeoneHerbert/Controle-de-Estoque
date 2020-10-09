package com.rns.testes.java.service;

import com.rns.testes.java.model.Estoque;
import com.rns.testes.java.model.Filial;
import com.rns.testes.java.model.Produto;
import com.rns.testes.java.model.enums.EnumTipoSolicitacaoEstoque;

import java.util.Set;

public interface IFilialService extends IGenericService<Filial,Long> {
    public Set<Estoque> findEstoque(Long idFilial);

    public Set<Estoque> updateEstoque(Long idFilial, Set<Produto> produtos, EnumTipoSolicitacaoEstoque tipoSolicitacaoEstoque);
}
