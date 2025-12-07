package br.upe.intsis.estoque.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class Consumer {

    private static final Logger log = LoggerFactory.getLogger(Consumer.class);

    @Autowired
    private Producer producer;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_ESTOQUE)
    public void receiveJson(InputData_Consulta_Estoque inputData_Consulta_Estoque) {
        log.info("Recurso recebido na fila de Estoque: {}", inputData_Consulta_Estoque.toString());

        //Variável para armazenar o resultado da verificação de estoque
        Boolean successStatus = false;

        // Tente processar o estoque na "->>> LÓGICA DE VERIFICAÇÃO E ATUALIZAÇÃO DO ESTOQUE <<<-"
        try {
            // ->>> LÓGICA DE VERIFICAÇÃO E ATUALIZAÇÃO DO ESTOQUE <<<-

            // *****************************************************************
            // A PESSOA QUE FOR DESENVOLVER A LÓGICA DEVE MUDAR ESTE BLOCO:
            // *****************************************************************

            // Exemplo da Lógica: Se o item existir no estoque e a quantidade for suficiente, defina como true
            boolean estoqueVerificado = true;
            if (estoqueVerificado) {
                successStatus = true;
            }

            // *****************************************************************

        } catch (Exception e) {
            log.error("Erro ao processar estoque para ID {}: {}", inputData_Consulta_Estoque.getId(), e.getMessage());
            successStatus = false;
        }


        //Cria o objeto de retorno usando a variável 'successStatus'
        OutputData_Estoque_Consulta outputDataEstoqueConsulta = new OutputData_Estoque_Consulta(
                inputData_Consulta_Estoque.getId(),
                successStatus
        );

        //Envia a resposta de status para o sistema de consultas
        producer.sendJson(outputDataEstoqueConsulta);
    }

}