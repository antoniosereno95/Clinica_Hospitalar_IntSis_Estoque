package br.upe.intsis.estoque.service;

import br.upe.intsis.estoque.model.Produto;
import br.upe.intsis.estoque.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> buscarTodos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public Optional<Produto> atualizar(Long id, Produto produtoDetalhes) {
        Optional<Produto> produtoExistenteOpt = buscarPorId(id);

        if (produtoExistenteOpt.isEmpty()) {
            return Optional.empty();
        }

        Produto produtoExistente = produtoExistenteOpt.get();

        produtoExistente.setNome(produtoDetalhes.getNome());
        produtoExistente.setDescricao(produtoDetalhes.getDescricao());
        produtoExistente.setCodigoInterno(produtoDetalhes.getCodigoInterno());
        produtoExistente.setUnidadeMedida(produtoDetalhes.getUnidadeMedida());
        produtoExistente.setCategoria(produtoDetalhes.getCategoria());
        produtoExistente.setDataValidadePadrao(produtoDetalhes.getDataValidadePadrao());

        return Optional.of(produtoRepository.save(produtoExistente));
    }

    public boolean deletar(Long id) {
        Optional<Produto> produtoOpt = buscarPorId(id);

        if (produtoOpt.isPresent()) {
            produtoRepository.delete(produtoOpt.get());
            return true;
        }
        return false;
    }
    
}
