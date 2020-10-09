package com.rns.testes.java.controller;

import com.rns.testes.java.controller.dto.FilialDto;
import com.rns.testes.java.controller.dto.mapper.FilialMapper;
import com.rns.testes.java.controller.event.HeaderLocationEvent;
import com.rns.testes.java.model.enums.EnumTipoFilial;
import com.rns.testes.java.model.Filial;
import com.rns.testes.java.service.IFilialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/filiais")
public class FilialController {
    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    IFilialService filialService;

    @GetMapping("/")
    public ResponseEntity<List<Filial>> findAll() {
        return ResponseEntity.ok(filialService.findAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<Filial> findById(@PathVariable Long id) {
        return ResponseEntity.ok(filialService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filial> update(@RequestBody FilialDto dto) {
        return ResponseEntity.ok(filialService.update(FilialMapper.INSTANCE.dtoToEntity(dto)));
    }

    @PostMapping("/")
    public ResponseEntity<Filial> insert(@Validated @RequestBody FilialDto dto, HttpServletResponse response) {
        Filial filial = filialService.save(FilialMapper.INSTANCE.dtoToEntity(dto));

        publisher.publishEvent(new HeaderLocationEvent(this, response, filial.getId()) );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(filial );

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        filialService.delete(id);
    }

    @GetMapping("/tipo/")
    public ResponseEntity<List<EnumTipoFilial>> findAllEnumTipoFilial() {
        return ResponseEntity.ok(EnumTipoFilial.getAll());
    }
}
