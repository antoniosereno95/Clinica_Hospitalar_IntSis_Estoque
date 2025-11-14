package br.upe.intsis.estoque.view;

import br.upe.intsis.estoque.model.Estoque;
import br.upe.intsis.estoque.service.EstoqueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/estoques")
public class EstoqueViewController {

    private final EstoqueService estoqueService;

    public EstoqueViewController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @GetMapping("/detalhes/{id}")
    public String detalhesEstoque(@PathVariable Long id, Model model) {
        Estoque estoque = estoqueService.buscarPorId(id)
                .orElse(null);
        if (estoque == null) {
            return "erro/404";
        }
        model.addAttribute("estoque", estoque);
        return "detalhes-estoque";
    }

    @GetMapping
    public String consultarEstoque(Model model) {
        model.addAttribute("estoques", estoqueService.listarTodos());
        return "consultar-estoque";
    }
}
