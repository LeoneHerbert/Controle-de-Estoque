package com.rns.testes.java.service.impl;

import com.rns.testes.java.controller.dto.FilialDto;
import com.rns.testes.java.controller.response.Erro;
import com.rns.testes.java.controller.validation.Validacao;
import com.rns.testes.java.repository.IFilialRepository;
import com.rns.testes.java.model.Filial;
import com.rns.testes.java.service.AbstractGenericServicePersistence;
import com.rns.testes.java.service.IFilialService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class FilialService extends AbstractGenericServicePersistence<IFilialRepository, Filial, Long> implements IFilialService {


    private boolean existe(List<Erro> erros) {
        return Objects.nonNull(erros) && !erros.isEmpty();
    }

    private List<Erro> getErros(FilialDto dto) {
        Validacao<FilialDto> validacao = new Validacao<>();
        return validacao.valida(dto);
    }
}
