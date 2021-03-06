package com.rns.testes.java.controller.dto.mapper;

import com.rns.testes.java.controller.dto.EstoqueDto;
import com.rns.testes.java.model.Estoque;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EstoqueMapper  extends GenericMapper<Estoque, EstoqueDto> {

    EstoqueMapper INSTANCE = Mappers.getMapper(EstoqueMapper.class);

}