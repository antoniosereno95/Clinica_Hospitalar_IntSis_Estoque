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

    @GetMapping
    public String listarEstoques(Model model) {
        List<Estoque> estoques = estoqueService.listarTodos();
        model.addAttribute("estoques", estoques);
        return "lista-estoques"; // nome do template em src/main/resources/templates
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
}
