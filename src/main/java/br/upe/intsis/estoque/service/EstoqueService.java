package br.upe.intsis.estoque.service;

import br.upe.intsis.estoque.amqp.InputData_Consulta_Estoque;
import br.upe.intsis.estoque.model.Estoque;
import br.upe.intsis.estoque.model.Produto;
import br.upe.intsis.estoque.repository.EstoqueRepository;
import br.upe.intsis.estoque.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstoqueService {
    private final EstoqueRepository estoqueRepository;
    private final ProdutoRepository produtoRepository;

    @Transactional(readOnly = true)
    public List<Estoque> listarTodos() {
        return estoqueRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Estoque> buscarPorId(Long id) {
        return estoqueRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Estoque> listarPorProduto(Long produtoId) {
        return estoqueRepository.findByProdutoId(produtoId);
    }

    @Transactional(readOnly = true)
    public List<Estoque> listarPorProdutoELocal(Long produtoId, String localizacao) {
        return estoqueRepository.findByProdutoIdAndLocalizacao(produtoId, localizacao);
    }

    @Transactional
    public Estoque entrada(Long produtoId, String lote, LocalDate validade, String localizacao, int quantidade) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + produtoId));

        Estoque estoque = estoqueRepository
                .findByProdutoAndLoteAndDataValidadeAndLocalizacao(produto, lote, validade, localizacao)
                .orElseGet(() -> new Estoque(null, produto, lote, validade, localizacao, 0));

        estoque.setQuantidade(estoque.getQuantidade() + quantidade);
        return estoqueRepository.save(estoque);
    }

    @Transactional
    public Estoque saida(Long produtoId, String lote, LocalDate validade, String localizacao, int quantidade) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + produtoId));

        Estoque estoque = estoqueRepository
                .findByProdutoAndLoteAndDataValidadeAndLocalizacao(produto, lote, validade, localizacao)
                .orElseThrow(() -> new IllegalArgumentException("Registro de estoque não encontrado"));

        int novoSaldo = estoque.getQuantidade() - quantidade;
        if (novoSaldo < 0) throw new IllegalArgumentException("Saldo insuficiente");

        estoque.setQuantidade(novoSaldo);
        return estoqueRepository.save(estoque);
    }

    @Transactional
    public Estoque ajustar(Long produtoId, String lote, LocalDate validade, String localizacao, int novaQuantidade) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + produtoId));

        Estoque estoque = estoqueRepository
                .findByProdutoAndLoteAndDataValidadeAndLocalizacao(produto, lote, validade, localizacao)
                .orElseGet(() -> new Estoque(null, produto, lote, validade, localizacao, 0));

        estoque.setQuantidade(novaQuantidade);
        return estoqueRepository.save(estoque);
    }

    @Transactional(readOnly = true)
    public boolean verificaEstoquePorNomeEQuantidade(String item , Integer quantidade) {

        if (quantidade == null || quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade requerida deve ser positiva.");
        }

        Optional<Produto> produtoOpt = produtoRepository.findByNome(item);

        if (produtoOpt.isEmpty()) {
            System.out.println("Produto '" + item + "' não encontrado no cadastro.");
            return false;
        }

        Produto produto = produtoOpt.get();

        Optional<Integer> totalEstoqueOpt = estoqueRepository.calcularTotalEstoquePorProduto(produto.getId());

        int quantidadeDisponivel = totalEstoqueOpt.orElse(0);

        boolean disponivel = quantidadeDisponivel >= quantidade;

        return disponivel;
    }

    //para teste
    public boolean verificaEstoque(InputData_Consulta_Estoque inputData_Consulta_Estoque) {

        int contadorChamadas = 0;

        contadorChamadas++;

        boolean resultado;
        if (contadorChamadas % 3 == 0) {
            resultado = false;
        } else {
            resultado = true;
        }
        return resultado;
    }
}
