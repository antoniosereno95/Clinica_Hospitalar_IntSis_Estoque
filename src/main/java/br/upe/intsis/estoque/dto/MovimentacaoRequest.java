package br.upe.intsis.estoque.dto;

import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class MovimentacaoRequest {
    @NotNull private Long produtoId;
    private String lote;
    private LocalDate dataValidade;
    private String localizacao;
    @Min(1) private int quantidade;
}

