package br.upe.intsis.estoque.amqp;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class Producer {

    private static final Logger log = LoggerFactory.getLogger(Producer.class);

    // Injeta o RabbitTemplate que já está configurado para JSON
    private final RabbitTemplate rabbitTemplate;

    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonConsulta(OutputData_Estoque_Consulta outputDataEstoqueConsulta) {
        log.info("Enviando JSON para a fila {}: {}", RabbitMQConfig.QUEUE_RETORNO, outputDataEstoqueConsulta.toString());

        // O convertAndSend usa o conversor JSON configurado para serializar o objeto
        // 1º: Exchange (o mesmo que a fila está ligada)
        // 2º: Routing Key (igual ao nome da fila de destino no Direct Exchange)
        // 3º: O Objeto Java (será serializado para JSON)
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_ESTOQUE,
                RabbitMQConfig.QUEUE_RETORNO,
                outputDataEstoqueConsulta
        );
    }

    public void sendJsonFinanceiro(OutputData_Estoque_Financeiro outputDataEstoqueFinanceiro) {
        log.info("Enviando JSON para a fila {}: {}", RabbitMQConfig.QUEUE_RETORNO, outputDataEstoqueFinanceiro.toString());

        // O convertAndSend usa o conversor JSON configurado para serializar o objeto
        // 1º: Exchange (o mesmo que a fila está ligada)
        // 2º: Routing Key (igual ao nome da fila de destino no Direct Exchange)
        // 3º: O Objeto Java (será serializado para JSON)
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_ESTOQUE,
                RabbitMQConfig.QUEUE_RETORNO,
                outputDataEstoqueFinanceiro
        );
    }
}
