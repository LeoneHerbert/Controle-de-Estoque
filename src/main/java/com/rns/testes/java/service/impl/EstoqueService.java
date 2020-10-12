package com.rns.testes.java.service.impl;

import com.rns.testes.java.controller.dto.EstoqueDto;
import com.rns.testes.java.model.Estoque;
import com.rns.testes.java.model.EstoquePk;
import com.rns.testes.java.model.Filial;
import com.rns.testes.java.model.Produto;
import com.rns.testes.java.model.enums.EnumTipoSolicitacaoEstoque;
import com.rns.testes.java.repository.IEstoqueRepository;
import com.rns.testes.java.service.AbstractGenericServicePersistence;
import com.rns.testes.java.service.IEstoqueService;
import com.rns.testes.java.service.IFilialService;
import com.rns.testes.java.service.IProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Service
public class EstoqueService extends AbstractGenericServicePersistence<IEstoqueRepository, Estoque, EstoquePk> implements IEstoqueService {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    IFilialService filialService;

    @Autowired
    IProdutoService produtoService;

    @Override
    public Estoque saveEstoque(EstoqueDto dto){
        Estoque estoque = new Estoque();
        estoque.setFilial(filialService.findById(dto.getFilial().getId()));
        estoque.setProduto(produtoService.findById(dto.getProduto().getId()));
        estoque.setQuantidadeDoProduto(dto.getQuantidadeDoProduto());
        return save(estoque);
    }

    @Override
    public Estoque updateProdutoFilial(Long idFilial, EstoqueDto dto){
        Filial filialAtual = filialService.findById(dto.getFilial().getId());
        Filial filialNova = filialService.findById(idFilial);
        Produto produto = produtoService.findById(dto.getProduto().getId());

        Estoque estoqueAtual = new Estoque();
        estoqueAtual.setFilial(filialAtual);
        estoqueAtual.setProduto(produto);
        estoqueAtual.setQuantidadeDoProduto(0);
        update(estoqueAtual);

        Estoque estoqueNovo = new Estoque();
        estoqueNovo.setProduto(produto);
        estoqueNovo.setFilial(filialNova);
        estoqueNovo.setQuantidadeDoProduto(dto.getQuantidadeDoProduto());
        save(estoqueNovo);

        return estoqueNovo;
    }

    @Override
    public Estoque updateEstoque(Integer quantidade, EstoqueDto dto, EnumTipoSolicitacaoEstoque solicitacao){
        Filial filial = filialService.findById(dto.getFilial().getId());
        Produto produto = produtoService.findById(dto.getProduto().getId());

        EstoquePk estoquePk = new EstoquePk();
        estoquePk.setProduto(produto);
        estoquePk.setFilial(filial);

        Estoque estoque = findById(estoquePk);

        switch (solicitacao) {
            case ENTRADA:
                estoque.entradaNoEstoque(quantidade);
                break;

            case SAIDA:
                estoque.saidaNoEstoque(quantidade);
                break;

            default:
                throw new RuntimeException("Tipo de pedido inválido");
        }
        update(estoque);

        return estoque;
    }

    @Override
    public void deleteEstoque(EstoqueDto dto){
        Filial filial = filialService.findById(dto.getFilial().getId());
        Produto produto = produtoService.findById(dto.getProduto().getId());

        EstoquePk estoquePk = new EstoquePk();
        estoquePk.setProduto(produto);
        estoquePk.setFilial(filial);

        delete(estoquePk);
    }

}
