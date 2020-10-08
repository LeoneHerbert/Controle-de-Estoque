package com.rns.testes.java.service.impl;

import com.rns.testes.java.repository.IFilialRepository;
import com.rns.testes.java.model.Filial;
import com.rns.testes.java.service.AbstractGenericServicePersistence;
import com.rns.testes.java.service.IFilialService;
import org.springframework.stereotype.Service;

@Service
public class FilialService extends AbstractGenericServicePersistence<IFilialRepository, Filial, Long> implements IFilialService {
}
