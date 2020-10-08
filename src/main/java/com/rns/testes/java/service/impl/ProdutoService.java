package com.rns.testes.java.service.impl;

import com.rns.testes.java.repository.IProdutoRepository;
import com.rns.testes.java.model.Produto;
import com.rns.testes.java.service.AbstractGenericServicePersistence;
import com.rns.testes.java.service.IProdutoService;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService extends AbstractGenericServicePersistence<IProdutoRepository, Produto, String> implements IProdutoService {
}
