package br.upe.intsis.estoque.view;

import br.upe.intsis.estoque.dto.MovimentacaoRequest;
import br.upe.intsis.estoque.model.Movimentacao;
import br.upe.intsis.estoque.service.MovimentacaoService;
import br.upe.intsis.estoque.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movimentacoes")
@RequiredArgsConstructor
public class MovimentacaoViewController {

    private final MovimentacaoService movimentacaoService;
    private final ProdutoService produtoService;


    @GetMapping("/novo")
    public String novaMovimentacaoForm(Model model) {
        model.addAttribute("movimentacao", new MovimentacaoRequest());
        model.addAttribute("produtos", produtoService.buscarTodos());
        return "nova-movimentacao";
    }


    @PostMapping("/novo")
    public String salvarMovimentacao(@ModelAttribute("movimentacao") MovimentacaoRequest dto,
                                     BindingResult result,
                                     Model model) {
        if (result.hasErrors()) {
            model.addAttribute("produtos", produtoService.buscarTodos());
            return "nova-movimentacao";
        }
        movimentacaoService.registrarMovimentacao(dto);
        return "redirect:/movimentacoes";
    }


    @GetMapping
    public String listarMovimentacoes(Model model) {
        List<Movimentacao> movs = movimentacaoService.buscarTodas();
        model.addAttribute("movimentacoes", movs);
        return "lista-movimentacoes";
    }


    @GetMapping("/detalhes/{id}")
    public String detalhesMovimentacao(@PathVariable Long id, Model model) {
        Movimentacao mov = movimentacaoService.buscarPorId(id)
                .orElse(null);
        if (mov == null) return "erro/404";
        model.addAttribute("movimentacao", mov);
        return "detalhes-movimentacao";
    }

    @PostMapping("/deletar/{id}")
    public String deletarMovimentacao(@PathVariable Long id) {
        movimentacaoService.deletarPorId(id);
        return "redirect:/movimentacoes";
    }

}
