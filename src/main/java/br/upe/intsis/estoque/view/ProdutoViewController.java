package br.upe.intsis.estoque.view;

import br.upe.intsis.estoque.model.Produto;
import br.upe.intsis.estoque.service.ProdutoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/produtos")
public class ProdutoViewController {

    private final ProdutoService produtoService;

    public ProdutoViewController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/detalhes/{id}")
    public String buscarProdutoParaView(@PathVariable Long id, Model model) {
        Produto produto = produtoService.buscarPorId(id).orElse(null);
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

    @GetMapping("/novo")
    public String novoProdutoForm(Model model) {
        model.addAttribute("produto", new Produto());
        return "novo-produto";
    }


    @PostMapping("/novo")
    public String salvarNovoProduto(@ModelAttribute Produto produto) {
        produtoService.salvar(produto);
        return "redirect:/produtos";
    }


    @PostMapping("/deletar/{id}")
    public String deletarProduto(@PathVariable Long id) {
        produtoService.deletar(id);
        return "redirect:/produtos";
    }
}
