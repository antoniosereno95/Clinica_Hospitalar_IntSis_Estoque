package br.upe.intsis.estoque.repository;

import br.upe.intsis.estoque.model.Movimentacao;
import br.upe.intsis.estoque.model.TipoMovimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    List<Movimentacao> findByProdutoIdOrderByDataMovimentacaoDesc(Long produtoId);

    List<Movimentacao> findByDataMovimentacaoBetween(LocalDateTime dataInicio, LocalDateTime dataFim);

    List<Movimentacao> findByTipo(TipoMovimentacao tipo);

}