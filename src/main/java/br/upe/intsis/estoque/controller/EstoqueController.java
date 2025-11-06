package br.upe.intsis.estoque.controller;

import br.upe.intsis.estoque.dto.MovimentacaoRequest;
import br.upe.intsis.estoque.dto.AjusteRequest;
import br.upe.intsis.estoque.model.Estoque;
import br.upe.intsis.estoque.service.EstoqueService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estoques")
public class EstoqueController {
    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<List<Estoque>> listarPorProduto(@PathVariable Long produtoId) {
        return ResponseEntity.ok(estoqueService.listarPorProduto(produtoId));
    }

    @PostMapping("/entrada")
    public ResponseEntity<Estoque> entrada(@RequestBody @Valid MovimentacaoRequest req) {
        Estoque estoque = estoqueService.entrada(
                req.getProdutoId(),
                req.getLote(),
                req.getDataValidade(),
                req.getLocalizacao(),
                req.getQuantidade()
        );
        return ResponseEntity.ok(estoque);
    }

    @PostMapping("/saida")
    public ResponseEntity<Estoque> saida(@RequestBody @Valid MovimentacaoRequest req) {
        Estoque estoque = estoqueService.saida(
                req.getProdutoId(),
                req.getLote(),
                req.getDataValidade(),
                req.getLocalizacao(),
                req.getQuantidade()
        );
        return ResponseEntity.ok(estoque);
    }

    @PutMapping("/ajuste")
    public ResponseEntity<Estoque> ajustar(@RequestBody @Valid AjusteRequest req) {
        Estoque estoque = estoqueService.ajustar(
                req.getProdutoId(),
                req.getLote(),
                req.getDataValidade(),
                req.getLocalizacao(),
                req.getNovaQuantidade()
        );
        return ResponseEntity.ok(estoque);
    }
}
