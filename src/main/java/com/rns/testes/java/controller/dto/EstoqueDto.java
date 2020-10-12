package com.rns.testes.java.controller.dto;
import lombok.Data;

@Data
public class EstoqueDto {

    private FilialDto filial;
    private ProdutoDto produto;
    private Integer quantidadeDoProduto;

}
