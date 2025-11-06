package br.upe.intsis.estoque.repository;

import br.upe.intsis.estoque.model.Estoque;
import br.upe.intsis.estoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    Optional<Estoque> findByProdutoAndLoteAndDataValidadeAndLocalizacao(
            Produto produto, String lote, LocalDate dataValidade, String localizacao);

    List<Estoque> findByProduto(Produto produto);

    List<Estoque> findByProdutoId(Long produtoId);

    List<Estoque> findByProdutoIdAndLocalizacao(Long produtoId, String localizacao);
}

