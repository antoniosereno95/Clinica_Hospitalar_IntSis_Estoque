package br.upe.intsis.estoque.dto;

import br.upe.intsis.estoque.model.TipoMovimentacao; // 1. IMPORTAR O ENUM
import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class MovimentacaoRequest {
    
    @NotNull 
    private Long produtoId;
    
    @NotNull
    private TipoMovimentacao tipo; 

    @NotNull 
    @Min(1)
    private Integer quantidade;

    private String lote;
    private LocalDate dataValidade;
    private String localizacao;

    private String observacao;
}