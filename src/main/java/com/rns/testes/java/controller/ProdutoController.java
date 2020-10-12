package com.rns.testes.java.controller;

import com.rns.testes.java.controller.dto.ProdutoDto;
import com.rns.testes.java.controller.dto.mapper.ProdutoMapper;
import com.rns.testes.java.controller.event.HeaderLocationEvent;
import com.rns.testes.java.controller.response.Resposta;
import com.rns.testes.java.model.Produto;
import com.rns.testes.java.service.IProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    IProdutoService produtoService;

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok(
                produtoService.findAll().stream().map(ProdutoMapper.INSTANCE::entityToDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable String id) {
        Produto produto = produtoService.findById(id);
        return ResponseEntity.ok(Resposta.comDadosDe(ProdutoMapper.INSTANCE.entityToDto(produto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody ProdutoDto produtoDto) {
        Produto produto = produtoService.update(ProdutoMapper.INSTANCE.dtoToEntity(produtoDto));
        return ResponseEntity.ok(Resposta.comDadosDe(ProdutoMapper.INSTANCE.entityToDto(produto)));
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody ProdutoDto produtoDto, HttpServletResponse response) {
        Produto produto = produtoService.save(ProdutoMapper.INSTANCE.dtoToEntity(produtoDto));

        publisher.publishEvent(new HeaderLocationEvent(this, response, produto.getId()) );

        return ResponseEntity.ok(Resposta.comDadosDe(ProdutoMapper.INSTANCE.entityToDto(produto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        produtoService.delete(id);
    }

}
