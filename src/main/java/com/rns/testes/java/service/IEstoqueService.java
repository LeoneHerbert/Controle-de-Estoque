package com.rns.testes.java.service;

import com.rns.testes.java.controller.dto.EstoqueDto;
import com.rns.testes.java.model.Estoque;
import com.rns.testes.java.model.EstoquePk;
import com.rns.testes.java.model.enums.EnumTipoSolicitacaoEstoque;

public interface IEstoqueService extends IGenericService<Estoque, EstoquePk> {
    Estoque saveEstoque(EstoqueDto dto);

    Estoque updateProdutoFilial(Long idFilial, EstoqueDto dto);

    Estoque updateEstoque(Integer quantidade, EstoqueDto dto, EnumTipoSolicitacaoEstoque solicitacao);

    void deleteEstoque(EstoqueDto estoqueDto);

}
