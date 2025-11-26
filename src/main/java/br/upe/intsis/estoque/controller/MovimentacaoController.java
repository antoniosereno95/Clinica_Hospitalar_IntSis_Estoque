package br.upe.intsis.estoque.controller;

import br.upe.intsis.estoque.dto.MovimentacaoRequest;
import br.upe.intsis.estoque.service.MovimentacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movimentacoes")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService movimentacaoService;

    @PostMapping
    public ResponseEntity<Void> registrarMovimentacao(@Valid @RequestBody MovimentacaoRequest dto) {

        movimentacaoService.registrarMovimentacao(dto);

        return ResponseEntity.ok().build();
    }

}