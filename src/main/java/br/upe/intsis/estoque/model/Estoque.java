package br.upe.intsis.estoque.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "estoques",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_estoque_produto_lote_validade_local",
                        columnNames = { "produto_id", "lote", "data_validade", "localizacao" }
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = false, foreignKey = @ForeignKey(name = "fk_estoque_produto"))
    private Produto produto;

    @Column(name = "lote", length = 100)
    @ToString.Include
    private String lote;

    @Column(name = "data_validade")
    @ToString.Include
    private LocalDate dataValidade;

    @Column(name = "localizacao", length = 100)
    @ToString.Include
    private String localizacao;

    @NotNull
    @Min(0)
    @Column(name = "quantidade", nullable = false)
    @ToString.Include
    private Integer quantidade = 0;
}
