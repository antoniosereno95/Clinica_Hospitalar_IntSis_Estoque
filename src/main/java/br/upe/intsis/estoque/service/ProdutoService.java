package br.upe.intsis.estoque.service;

import br.upe.intsis.estoque.model.Produto;
import br.upe.intsis.estoque.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import br.upe.intsis.estoque.repository.EstoqueRepository;
import br.upe.intsis.estoque.repository.MovimentacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final EstoqueRepository estoqueRepository;
    private final MovimentacaoRepository movimentacaoRepository;

    public ProdutoService(ProdutoRepository produtoRepository,
                          EstoqueRepository estoqueRepository,
                          MovimentacaoRepository movimentacaoRepository) {
        this.produtoRepository = produtoRepository;
        this.estoqueRepository = estoqueRepository;
        this.movimentacaoRepository = movimentacaoRepository;
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

    @Transactional
    public void deletarProdutoComDependencias(Long produtoId) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        movimentacaoRepository.deleteAllByProduto(produto);
        estoqueRepository.deleteAllByProduto(produto);
        produtoRepository.delete(produto);
    }
    
}
