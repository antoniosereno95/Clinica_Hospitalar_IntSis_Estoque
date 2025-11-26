package br.upe.intsis.estoque.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimentacoes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull 
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "produto_id", nullable = false, 
                foreignKey = @ForeignKey(name = "fk_movimentacao_produto"))
    private Produto produto;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Integer quantidade;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoMovimentacao tipo;

    @NotNull
    @PastOrPresent
    @Column(nullable = false)
    private LocalDateTime dataMovimentacao;

    @Column(length = 100)
    private String lote;

    @Column
    private LocalDate dataValidade;

    @Column(length = 100)
    private String localizacao;

    @Column(length = 255)
    private String observacao;

}