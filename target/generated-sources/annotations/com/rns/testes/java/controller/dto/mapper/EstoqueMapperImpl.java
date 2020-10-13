package com.rns.testes.java.controller.dto.mapper;

import com.rns.testes.java.controller.dto.EstoqueDto;
import com.rns.testes.java.controller.dto.FilialDto;
import com.rns.testes.java.controller.dto.ProdutoDto;
import com.rns.testes.java.model.Estoque;
import com.rns.testes.java.model.Filial;
import com.rns.testes.java.model.Produto;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-12T21:48:33-0300",
    comments = "version: 1.3.0.Beta2, compiler: javac, environment: Java 11.0.6 (JetBrains s.r.o)"
)
public class EstoqueMapperImpl implements EstoqueMapper {

    @Override
    public EstoqueDto entityToDto(Estoque entity) {
        if ( entity == null ) {
            return null;
        }

        EstoqueDto estoqueDto = new EstoqueDto();

        estoqueDto.setFilial( filialToFilialDto( entity.getFilial() ) );
        estoqueDto.setProduto( produtoToProdutoDto( entity.getProduto() ) );
        estoqueDto.setQuantidadeDoProduto( entity.getQuantidadeDoProduto() );

        return estoqueDto;
    }

    @Override
    public Estoque dtoToEntity(EstoqueDto dto) {
        if ( dto == null ) {
            return null;
        }

        Estoque estoque = new Estoque();

        estoque.setProduto( produtoDtoToProduto( dto.getProduto() ) );
        estoque.setFilial( filialDtoToFilial( dto.getFilial() ) );
        estoque.setQuantidadeDoProduto( dto.getQuantidadeDoProduto() );

        return estoque;
    }

    protected FilialDto filialToFilialDto(Filial filial) {
        if ( filial == null ) {
            return null;
        }

        FilialDto filialDto = new FilialDto();

        filialDto.setVersao( filial.getVersao() );
        filialDto.setDataUltAlteracao( filial.getDataUltAlteracao() );
        filialDto.setId( filial.getId() );
        filialDto.setRazaoSocial( filial.getRazaoSocial() );
        filialDto.setCnpj( filial.getCnpj() );
        filialDto.setEndereco( filial.getEndereco() );
        filialDto.setTipoFilial( filial.getTipoFilial() );

        return filialDto;
    }

    protected ProdutoDto produtoToProdutoDto(Produto produto) {
        if ( produto == null ) {
            return null;
        }

        ProdutoDto produtoDto = new ProdutoDto();

        produtoDto.setId( produto.getId() );
        produtoDto.setNome( produto.getNome() );

        return produtoDto;
    }

    protected Produto produtoDtoToProduto(ProdutoDto produtoDto) {
        if ( produtoDto == null ) {
            return null;
        }

        Produto produto = new Produto();

        produto.setId( produtoDto.getId() );
        produto.setNome( produtoDto.getNome() );

        return produto;
    }

    protected Filial filialDtoToFilial(FilialDto filialDto) {
        if ( filialDto == null ) {
            return null;
        }

        Filial filial = new Filial();

        filial.setVersao( filialDto.getVersao() );
        filial.setDataUltAlteracao( filialDto.getDataUltAlteracao() );
        filial.setId( filialDto.getId() );
        filial.setRazaoSocial( filialDto.getRazaoSocial() );
        filial.setCnpj( filialDto.getCnpj() );
        filial.setEndereco( filialDto.getEndereco() );
        filial.setTipoFilial( filialDto.getTipoFilial() );

        return filial;
    }
}
