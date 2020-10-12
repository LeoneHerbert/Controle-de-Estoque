package com.rns.testes.java.controller;

import com.rns.testes.java.controller.dto.FilialDto;
import com.rns.testes.java.controller.dto.mapper.FilialMapper;
import com.rns.testes.java.controller.event.HeaderLocationEvent;
import com.rns.testes.java.controller.response.Erro;
import com.rns.testes.java.controller.response.Resposta;
import com.rns.testes.java.controller.validation.Validacao;
import com.rns.testes.java.model.Filial;
import com.rns.testes.java.model.enums.EnumTipoFilial;
import com.rns.testes.java.service.IFilialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/filial")
public class FilialController {
    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    IFilialService filialService;

    @GetMapping
    public ResponseEntity findAll() {

        return ResponseEntity.ok(
                filialService.findAll().stream().map(FilialMapper.INSTANCE::entityToDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        Filial filial = filialService.findById(id);
        return ResponseEntity.ok(Resposta.comDadosDe(FilialMapper.INSTANCE.entityToDto(filial)));
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody FilialDto filialDto) {
        List<Erro> erros = this.getErros(filialDto);
        if (existe(erros)) {
            return ResponseEntity.badRequest().body(Resposta.com(erros));
        }

        Filial filial = filialService.update(FilialMapper.INSTANCE.dtoToEntity(filialDto));

        return ResponseEntity.ok(Resposta.comDadosDe(FilialMapper.INSTANCE.entityToDto(filial)));
    }

    @PostMapping
    public ResponseEntity insert(@Validated @RequestBody FilialDto filialDto, HttpServletResponse response) {
        Filial filial = filialService.save(FilialMapper.INSTANCE.dtoToEntity(filialDto));

        publisher.publishEvent(new HeaderLocationEvent(this, response, filial.getId()));

        return ResponseEntity.ok(Resposta.comDadosDe(FilialMapper.INSTANCE.entityToDto(filial)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        filialService.delete(id);
    }

    @GetMapping("/tipo")
    public ResponseEntity findAllEnumTipoFilial() {
        return ResponseEntity.ok(Resposta.comDadosDe(EnumTipoFilial.getAll()));
    }

    private boolean existe(List<Erro> erros) {
        return Objects.nonNull(erros) && !erros.isEmpty();
    }

    private List<Erro> getErros(FilialDto dto) {
        Validacao<FilialDto> validacao = new Validacao<>();
        return validacao.valida(dto);
    }
}
