package br.upe.intsis.estoque;

import br.upe.intsis.estoque.model.CategoriaProduto;
import br.upe.intsis.estoque.model.Produto;
import br.upe.intsis.estoque.model.UnidadeMedida;
import br.upe.intsis.estoque.repository.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner carregarProdutosIniciais(ProdutoRepository produtoRepository) {
        return args -> {

            if (produtoRepository.count() > 0) {
                return;
            }

            Produto p1 = new Produto();
            p1.setNome("Paracetamol 500mg");
            p1.setCodigoInterno("MED-001");
            p1.setDescricao("Analgésico e antitérmico");
            p1.setUnidadeMedida(UnidadeMedida.UNIDADE);
            p1.setCategoria(CategoriaProduto.MEDICAMENTO);
            p1.setLote("L123A");
            p1.setDataValidadePadrao(LocalDate.of(2026, 12, 31));

            Produto p2 = new Produto();
            p2.setNome("Dipirona 1g");
            p2.setCodigoInterno("MED-002");
            p2.setDescricao("Analgésico e antitérmico");
            p2.setUnidadeMedida(UnidadeMedida.UNIDADE);
            p2.setCategoria(CategoriaProduto.MEDICAMENTO);
            p2.setLote("L124B");
            p2.setDataValidadePadrao(LocalDate.of(2027, 6, 30));

            Produto p3 = new Produto();
            p3.setNome("Amoxicilina 500mg");
            p3.setCodigoInterno("MED-003");
            p3.setDescricao("Antibiótico");
            p3.setUnidadeMedida(UnidadeMedida.UNIDADE);
            p3.setCategoria(CategoriaProduto.MEDICAMENTO);
            p3.setLote("AMX-001");
            p3.setDataValidadePadrao(LocalDate.of(2026, 3, 15));

            Produto p4 = new Produto();
            p4.setNome("Seringa 3ml");
            p4.setCodigoInterno("INS-001");
            p4.setDescricao("Seringa descartável 3ml");
            p4.setUnidadeMedida(UnidadeMedida.UNIDADE);
            p4.setCategoria(CategoriaProduto.INSUMO);
            p4.setLote("SRG3-01");
            p4.setDataValidadePadrao(LocalDate.of(2028, 1, 1));

            Produto p5 = new Produto();
            p5.setNome("Seringa 10ml");
            p5.setCodigoInterno("INS-002");
            p5.setDescricao("Seringa descartável 10ml");
            p5.setUnidadeMedida(UnidadeMedida.UNIDADE);
            p5.setCategoria(CategoriaProduto.INSUMO);
            p5.setLote("SRG10-01");
            p5.setDataValidadePadrao(LocalDate.of(2028, 1, 1));

            Produto p6 = new Produto();
            p6.setNome("Agulha 25x7");
            p6.setCodigoInterno("INS-003");
            p6.setDescricao("Agulha descartável 25x7");
            p6.setUnidadeMedida(UnidadeMedida.UNIDADE);
            p6.setCategoria(CategoriaProduto.INSUMO);
            p6.setLote("AGU-257");
            p6.setDataValidadePadrao(LocalDate.of(2029, 12, 31));

            Produto p7 = new Produto();
            p7.setNome("Algodão hidrófilo 500g");
            p7.setCodigoInterno("INS-004");
            p7.setDescricao("Algodão hidrófilo rolo 500g");
            p7.setUnidadeMedida(UnidadeMedida.PACOTE);
            p7.setCategoria(CategoriaProduto.INSUMO);
            p7.setLote("ALG-500");
            p7.setDataValidadePadrao(LocalDate.of(2027, 9, 30));

            Produto p8 = new Produto();
            p8.setNome("Gaze estéril 7,5x7,5");
            p8.setCodigoInterno("INS-005");
            p8.setDescricao("Gaze estéril compressa");
            p8.setUnidadeMedida(UnidadeMedida.CAIXA);
            p8.setCategoria(CategoriaProduto.INSUMO);
            p8.setLote("GAZ-001");
            p8.setDataValidadePadrao(LocalDate.of(2026, 11, 30));

            Produto p9 = new Produto();
            p9.setNome("Luva de procedimento P");
            p9.setCodigoInterno("EQP-001");
            p9.setDescricao("Luva descartável tamanho P");
            p9.setUnidadeMedida(UnidadeMedida.CAIXA);
            p9.setCategoria(CategoriaProduto.EQUIPAMENTO_DESCARTAVEL);
            p9.setLote("LUV-P-01");
            p9.setDataValidadePadrao(LocalDate.of(2026, 5, 31));

            Produto p10 = new Produto();
            p10.setNome("Luva de procedimento M");
            p10.setCodigoInterno("EQP-002");
            p10.setDescricao("Luva descartável tamanho M");
            p10.setUnidadeMedida(UnidadeMedida.CAIXA);
            p10.setCategoria(CategoriaProduto.EQUIPAMENTO_DESCARTAVEL);
            p10.setLote("LUV-M-01");
            p10.setDataValidadePadrao(LocalDate.of(2026, 5, 31));

            Produto p11 = new Produto();
            p11.setNome("Luva de procedimento G");
            p11.setCodigoInterno("EQP-003");
            p11.setDescricao("Luva descartável tamanho G");
            p11.setUnidadeMedida(UnidadeMedida.CAIXA);
            p11.setCategoria(CategoriaProduto.EQUIPAMENTO_DESCARTAVEL);
            p11.setLote("LUV-G-01");
            p11.setDataValidadePadrao(LocalDate.of(2026, 5, 31));

            Produto p12 = new Produto();
            p12.setNome("Máscara cirúrgica tripla");
            p12.setCodigoInterno("EQP-004");
            p12.setDescricao("Máscara cirúrgica descartável tripla camada");
            p12.setUnidadeMedida(UnidadeMedida.CAIXA);
            p12.setCategoria(CategoriaProduto.EQUIPAMENTO_DESCARTAVEL);
            p12.setLote("MSC-001");
            p12.setDataValidadePadrao(LocalDate.of(2027, 2, 28));

            Produto p13 = new Produto();
            p13.setNome("Touca descartável");
            p13.setCodigoInterno("EQP-005");
            p13.setDescricao("Touca sanfonada descartável");
            p13.setUnidadeMedida(UnidadeMedida.CAIXA);
            p13.setCategoria(CategoriaProduto.EQUIPAMENTO_DESCARTAVEL);
            p13.setLote("TOU-001");
            p13.setDataValidadePadrao(LocalDate.of(2027, 2, 28));

            Produto p14 = new Produto();
            p14.setNome("Álcool 70% 1L");
            p14.setCodigoInterno("LIM-001");
            p14.setDescricao("Álcool etílico 70% frasco 1L");
            p14.setUnidadeMedida(UnidadeMedida.LITRO);
            p14.setCategoria(CategoriaProduto.LIMPEZA);
            p14.setLote("ALC-70-1");
            p14.setDataValidadePadrao(LocalDate.of(2028, 8, 31));

            Produto p15 = new Produto();
            p15.setNome("Detergente enzimático");
            p15.setCodigoInterno("LIM-002");
            p15.setDescricao("Detergente enzimático para instrumentais");
            p15.setUnidadeMedida(UnidadeMedida.LITRO);
            p15.setCategoria(CategoriaProduto.LIMPEZA);
            p15.setLote("DET-ENZ-01");
            p15.setDataValidadePadrao(LocalDate.of(2027, 12, 31));

            Produto p16 = new Produto();
            p16.setNome("Hipoclorito de sódio 1L");
            p16.setCodigoInterno("LIM-003");
            p16.setDescricao("Solução de hipoclorito para desinfecção");
            p16.setUnidadeMedida(UnidadeMedida.LITRO);
            p16.setCategoria(CategoriaProduto.LIMPEZA);
            p16.setLote("HIPO-001");
            p16.setDataValidadePadrao(LocalDate.of(2026, 10, 31));

            Produto p17 = new Produto();
            p17.setNome("Soro fisiológico 0,9% 500ml");
            p17.setCodigoInterno("MED-004");
            p17.setDescricao("Soro fisiológico para uso hospitalar");
            p17.setUnidadeMedida(UnidadeMedida.FRASCO);
            p17.setCategoria(CategoriaProduto.MEDICAMENTO);
            p17.setLote("SORO-500");
            p17.setDataValidadePadrao(LocalDate.of(2027, 4, 30));

            Produto p18 = new Produto();
            p18.setNome("Soro glicosado 5% 500ml");
            p18.setCodigoInterno("MED-005");
            p18.setDescricao("Soro glicosado 5%");
            p18.setUnidadeMedida(UnidadeMedida.FRASCO);
            p18.setCategoria(CategoriaProduto.MEDICAMENTO);
            p18.setLote("SGL-500");
            p18.setDataValidadePadrao(LocalDate.of(2027, 4, 30));

            Produto p19 = new Produto();
            p19.setNome("Fita microporosa 5cm x 10m");
            p19.setCodigoInterno("INS-006");
            p19.setDescricao("Fita adesiva hipoalergênica");
            p19.setUnidadeMedida(UnidadeMedida.ROLO);
            // Se não tiver ROLO na enum, use METRO:
            // p19.setUnidadeMedida(UnidadeMedida.METRO);
            p19.setCategoria(CategoriaProduto.INSUMO);
            p19.setLote("FITA-001");
            p19.setDataValidadePadrao(LocalDate.of(2028, 3, 31));

            Produto p20 = new Produto();
            p20.setNome("Gaze em rolo 10m");
            p20.setCodigoInterno("MAT-001");
            p20.setDescricao("Gaze hidrófila em rolo");
            p20.setUnidadeMedida(UnidadeMedida.METRO);
            p20.setCategoria(CategoriaProduto.MATERIA_PRIMA);
            p20.setLote("GAZ-ROL-01");
            p20.setDataValidadePadrao(LocalDate.of(2028, 6, 30));

            produtoRepository.save(p1);
            produtoRepository.save(p2);
            produtoRepository.save(p3);
            produtoRepository.save(p4);
            produtoRepository.save(p5);
            produtoRepository.save(p6);
            produtoRepository.save(p7);
            produtoRepository.save(p8);
            produtoRepository.save(p9);
            produtoRepository.save(p10);
            produtoRepository.save(p11);
            produtoRepository.save(p12);
            produtoRepository.save(p13);
            produtoRepository.save(p14);
            produtoRepository.save(p15);
            produtoRepository.save(p16);
            produtoRepository.save(p17);
            produtoRepository.save(p18);
            produtoRepository.save(p19);
            produtoRepository.save(p20);
        };
    }
}
