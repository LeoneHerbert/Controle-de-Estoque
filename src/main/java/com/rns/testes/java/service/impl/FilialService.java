package com.rns.testes.java.service.impl;

import com.rns.testes.java.model.Estoque;
import com.rns.testes.java.model.Produto;
import com.rns.testes.java.model.enums.EnumTipoSolicitacaoEstoque;
import com.rns.testes.java.repository.IFilialRepository;
import com.rns.testes.java.model.Filial;
import com.rns.testes.java.service.AbstractGenericServicePersistence;
import com.rns.testes.java.service.IFilialService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FilialService extends AbstractGenericServicePersistence<IFilialRepository, Filial, Long> implements IFilialService {
    @Override
    public Set<Estoque> findEstoque(Long idFilial) {
        Filial filial = findById(idFilial);

        return filial.getProdutosNoEstoque();
    }

    @Override
    public Set<Estoque> updateEstoque(Long idFilial, Set<Produto> produtos, EnumTipoSolicitacaoEstoque tipoSolicitacaoEstoque) {
        Filial filial = findById(idFilial);

        for (Produto produto : produtos) {

            Estoque estoque = new Estoque();
            estoque.setFilial(filial);
            estoque.setProduto(produto);
            estoque.entradaNoEstoque(produtos.size());

            switch (tipoSolicitacaoEstoque) {
                case ENTRADA:
                    filial.adicionaNoEstoque(estoque);
                    break;

                case SAIDA:
                    filial.retiraDoEstoque(estoque);
                    break;

                default:
                    throw new RuntimeException("Solicitação inválida");
            }

        }

        return save(filial).getProdutosNoEstoque();
    }
}
