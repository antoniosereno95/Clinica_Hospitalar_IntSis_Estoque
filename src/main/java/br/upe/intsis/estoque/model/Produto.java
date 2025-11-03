package br.upe.intsis.estoque.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.*;

import java.time.LocalDate;

//Entidade que representa um Produto ou Material dentro do estoque hospitalar.

@Entity
@Table(name = "produtos")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(unique = true, length = 50)
    private String codigoInterno; //codigo interno para ser registrado nas movimentações

    @Column(length = 255)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private UnidadeMedida unidadeMedida; // Usa a enumeração abaixo

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private CategoriaProduto categoria; // Usa a enumeração abaixo

    @Column
    private String lote;

    @Column
    private LocalDate dataValidadePadrao;

}

enum UnidadeMedida {
    UNIDADE,
    CAIXA,
    PACOTE,
    FRASCO,
    LITRO,
    MILILITRO,
    METRO
}

enum CategoriaProduto {
    MEDICAMENTO,
    INSUMO,
    EQUIPAMENTO_DESCARTAVEL,
    LIMPEZA,
    MATERIA_PRIMA
}