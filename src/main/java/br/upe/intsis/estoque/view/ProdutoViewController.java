package br.upe.intsis.estoque.view;

import br.upe.intsis.estoque.model.Produto;
import br.upe.intsis.estoque.service.ProdutoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/produtos") //Rota base mais amigável sem o "api" para evitar ambiguidade com o REST API
public class ProdutoViewController {

    private final ProdutoService produtoService;

    public ProdutoViewController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/detalhes/{id}") //Nova rota "detalhes" para View
    public String buscarProdutoParaView(@PathVariable Long id, Model model) {

        Produto produto = produtoService.buscarPorId(id)
                .orElse(null);

        if (produto == null) {
            return "erro/404";
        }

        model.addAttribute("produto", produto);

        return "detalhes-produto";
    }

    @GetMapping
    public String listarProdutosParaView(Model model) {
        model.addAttribute("produtos", produtoService.buscarTodos());
        return "lista-produtos";
    }

}