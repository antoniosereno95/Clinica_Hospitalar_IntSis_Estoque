package br.upe.intsis.estoque.repository;

import br.upe.intsis.estoque.model.Estoque;
import br.upe.intsis.estoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    Optional<Estoque> findByProdutoAndLoteAndDataValidadeAndLocalizacao(
            Produto produto, String lote, LocalDate dataValidade, String localizacao);

    List<Estoque> findByProduto(Produto produto);

    List<Estoque> findByProdutoId(Long produtoId);

    List<Estoque> findByProdutoIdAndLocalizacao(Long produtoId, String localizacao);

    void deleteAllByProduto(Produto produto);

    @Query("SELECT SUM(e.quantidade) FROM Estoque e WHERE e.produto.id = :produtoId")
    Optional<Integer> calcularTotalEstoquePorProduto(@Param("produtoId") Long produtoId);
}

