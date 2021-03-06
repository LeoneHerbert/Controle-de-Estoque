package com.rns.testes.java.controller;

import com.rns.testes.java.controller.dto.EstoqueDto;
import com.rns.testes.java.controller.dto.mapper.EstoqueMapper;
import com.rns.testes.java.controller.event.HeaderLocationEvent;
import com.rns.testes.java.controller.response.Erro;
import com.rns.testes.java.controller.response.Resposta;
import com.rns.testes.java.controller.validation.Validacao;
import com.rns.testes.java.model.Estoque;
import com.rns.testes.java.model.enums.EnumTipoSolicitacaoEstoque;
import com.rns.testes.java.service.IEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/estoque")
public class EstoqueController {
    @Autowired
    IEstoqueService estoqueService;
    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok(
                estoqueService.findAll().stream().map(EstoqueMapper.INSTANCE::entityToDto));
    }

    @PutMapping
    public ResponseEntity update(@Valid @RequestBody EstoqueDto estoqueDto,
                                 @RequestParam EnumTipoSolicitacaoEstoque solicitacao,
                                 @RequestParam Integer quantidade) {
        List<Erro> erros = this.getErros(estoqueDto);

        if (existe(erros)) {
            return ResponseEntity.badRequest().body(Resposta.com(erros));
        }

        Estoque estoque = estoqueService.updateEstoque(quantidade, estoqueDto, solicitacao);

        return ResponseEntity.ok(Resposta.comDadosDe(EstoqueMapper.INSTANCE.entityToDto(estoque)));
    }

    @PostMapping("/transfere_produto_para_filial/{idFilial}")
    public ResponseEntity updateProdutoFilial(@PathVariable Long idFilial, @Valid @RequestBody EstoqueDto estoqueDto) {
        List<Erro> erros = this.getErros(estoqueDto);

        if (existe(erros)) {
            return ResponseEntity.badRequest().body(Resposta.com(erros));
        }
        Estoque estoque = estoqueService.updateProdutoFilial(idFilial, estoqueDto);

        return ResponseEntity.ok(Resposta.comDadosDe(EstoqueMapper.INSTANCE.entityToDto(estoque)));
    }

    @PostMapping
    public ResponseEntity insert(@Valid @RequestBody EstoqueDto estoqueDto,
                                 HttpServletResponse response) {

        Estoque estoque = estoqueService.saveEstoque(estoqueDto);

        publisher.publishEvent(new HeaderLocationEvent(this, response, estoque.getId()));

        return ResponseEntity.ok(Resposta.comDadosDe(EstoqueMapper.INSTANCE.entityToDto(estoque)));
    }

    @DeleteMapping
    public void delete(@Valid @RequestBody EstoqueDto estoqueDto) {
        estoqueService.deleteEstoque(estoqueDto);
    }

    private boolean existe(List<Erro> erros) {
        return Objects.nonNull(erros) && !erros.isEmpty();
    }

    private List<Erro> getErros(EstoqueDto estoqueDto) {
        Validacao<EstoqueDto> validacao = new Validacao<>();
        return validacao.valida(estoqueDto);
    }
}
