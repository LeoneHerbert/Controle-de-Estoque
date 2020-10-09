package com.rns.testes.java.controller;

import com.rns.testes.java.controller.dto.ProdutoDto;
import com.rns.testes.java.controller.dto.mapper.FilialMapper;
import com.rns.testes.java.controller.dto.mapper.ProdutoMapper;
import com.rns.testes.java.controller.event.HeaderLocationEvent;
import com.rns.testes.java.model.Produto;
import com.rns.testes.java.service.IProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    IProdutoService service;

    @GetMapping("/")
    public ResponseEntity<List<Produto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@RequestBody ProdutoDto dto) {
        return ResponseEntity.ok(service.update(ProdutoMapper.INSTANCE.dtoToEntity(dto)));
    }

    @PostMapping("/")
    public ResponseEntity<Produto> insert(@RequestBody ProdutoDto dto, HttpServletResponse response) {
        Produto produto = service.save(ProdutoMapper.INSTANCE.dtoToEntity(dto));

        publisher.publishEvent(new HeaderLocationEvent(this, response, produto.getId()) );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(produto );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

}
