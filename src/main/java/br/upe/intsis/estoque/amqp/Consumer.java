package br.upe.intsis.estoque.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class Consumer {

    private static final Logger log = LoggerFactory.getLogger(Consumer.class);

    // O objeto InputData será preenchido automaticamente com o JSON recebido
    @RabbitListener(queues = RabbitMQConfig.QUEUE_RECEBIMENTO)
    public void receiveJson(InputData inputData) {
        log.info("JSON Recebido da fila {}: {}", RabbitMQConfig.QUEUE_RECEBIMENTO, inputData.toString());

        // --- Lógica de Processamento ---
        // Exemplo: processar o InputData e gerar um OutputData
        OutputData outputData = processData(inputData);

    }

    private OutputData processData(InputData inputData) {
        // Implemente sua lógica de transformação/processamento aqui
        return new OutputData("ID-" + inputData.getId(), "Processado: " + inputData.getMessage());
    }
}