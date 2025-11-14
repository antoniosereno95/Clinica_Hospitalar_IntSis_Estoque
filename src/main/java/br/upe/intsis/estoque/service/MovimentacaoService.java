package br.upe.intsis.estoque.service;

import br.upe.intsis.estoque.dto.MovimentacaoRequest;
import br.upe.intsis.estoque.model.*;
import br.upe.intsis.estoque.repository.EstoqueRepository;
import br.upe.intsis.estoque.repository.MovimentacaoRepository;
import br.upe.intsis.estoque.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovimentacaoService {

    private final EstoqueRepository estoqueRepository;
    private final ProdutoRepository produtoRepository;
    private final MovimentacaoRepository movimentacaoRepository;

    @Transactional(rollbackFor = Exception.class)
    public Movimentacao registrarMovimentacao(MovimentacaoRequest dto) {
        Produto produto = produtoRepository.findById(dto.getProdutoId())
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com ID: " + dto.getProdutoId()));

        List<Estoque> estoquesDoProduto = estoqueRepository.findByProduto(produto);

        Optional<Estoque> estoqueOpt = estoquesDoProduto.stream()
                .filter(e -> Objects.equals(e.getLote(), dto.getLote()))
                .filter(e -> Objects.equals(e.getDataValidade(), dto.getDataValidade()))
                .filter(e -> Objects.equals(e.getLocalizacao(), dto.getLocalizacao()))
                .findFirst();

        Estoque estoque;

        if (dto.getTipo() == TipoMovimentacao.ENTRADA) {
            if (estoqueOpt.isPresent()) {
                estoque = estoqueOpt.get();
                estoque.setQuantidade(estoque.getQuantidade() + dto.getQuantidade());
            } else {
                estoque = new Estoque();
                estoque.setProduto(produto);
                estoque.setLote(dto.getLote());
                estoque.setDataValidade(dto.getDataValidade());
                estoque.setLocalizacao(dto.getLocalizacao());
                estoque.setQuantidade(dto.getQuantidade());
            }
        } else if (dto.getTipo() == TipoMovimentacao.AJUSTE) {
            if (estoqueOpt.isPresent()) {
                estoque = estoqueOpt.get();
                estoque.setQuantidade(dto.getQuantidade());
            } else {
                estoque = new Estoque();
                estoque.setProduto(produto);
                estoque.setLote(dto.getLote());
                estoque.setDataValidade(dto.getDataValidade());
                estoque.setLocalizacao(dto.getLocalizacao());
                estoque.setQuantidade(dto.getQuantidade());
            }
        } else { // TipoMovimentacao.SAIDA ou outros
            if (estoqueOpt.isEmpty()) {
                throw new EntityNotFoundException("Estoque não encontrado para este produto/lote/validade/localização.");
            }
            estoque = estoqueOpt.get();
            int novaQuantidade = estoque.getQuantidade() - dto.getQuantidade();

            if (novaQuantidade < 0) {
                throw new RuntimeException(
                        "Estoque insuficiente para o produto: " + produto.getNome() +
                                " (Lote: " + dto.getLote() + "). " +
                                "Disponível: " + estoque.getQuantidade() + ", Pedido: " + dto.getQuantidade()
                );
            }
            estoque.setQuantidade(novaQuantidade);
        }

        estoqueRepository.save(estoque);

        Movimentacao log = Movimentacao.builder()
                .produto(produto)
                .quantidade(dto.getQuantidade())
                .tipo(dto.getTipo())
                .dataMovimentacao(LocalDateTime.now())
                .lote(dto.getLote())
                .dataValidade(dto.getDataValidade())
                .localizacao(dto.getLocalizacao())
                .observacao(dto.getObservacao())
                .build();

        return movimentacaoRepository.save(log);
    }

    @Transactional(readOnly = true)
    public List<Movimentacao> buscarTodas() {
        return movimentacaoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Movimentacao> buscarPorId(Long id) {
        return movimentacaoRepository.findById(id);
    }

    @Transactional
    public void deletarPorId(Long id) {
        movimentacaoRepository.deleteById(id);
    }

}
