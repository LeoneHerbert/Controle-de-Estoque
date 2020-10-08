package com.rns.testes.java.dto.mapper;

import com.rns.testes.java.dto.EstoqueDto;
import com.rns.testes.java.model.Estoque;
import org.mapstruct.factory.Mappers;

public interface EstoqueMapper  extends GenericMapper<Estoque, EstoqueDto> {

    EstoqueMapper INSTANCE = Mappers.getMapper(EstoqueMapper.class);

}