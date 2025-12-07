package br.upe.intsis.estoque.amqp;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    //Conversor de mensagens JSON (Jackson)
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    //Nomes das Filas Exemplo
    public static final String QUEUE_RECEBIMENTO = "fila.recebimento.json";
    public static final String QUEUE_ENVIO = "fila.envio.json";
    public static final String EXCHANGE_NAME = "meu.exchange";

    //Fila de Recebimento
    @Bean
    public Queue recebimentoQueue() {
        return new Queue(QUEUE_RECEBIMENTO, true); // true = fila persistente
    }

    //Fila de Envio
    @Bean
    public Queue envioQueue() {
        return new Queue(QUEUE_ENVIO, true);
    }

    //Exchange
    @Bean
    public Exchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    //Liga a Fila de Recebimento ao Exchange
    @Bean
    public Binding recebimentoBinding(Queue recebimentoQueue, DirectExchange exchange) {
        return BindingBuilder.bind(recebimentoQueue).to(exchange).with(QUEUE_RECEBIMENTO); // Usa o nome da fila como Routing Key
    }

    //Liga a Fila de Envio ao Exchange
    @Bean
    public Binding envioBinding(Queue envioQueue, DirectExchange exchange) {
        return BindingBuilder.bind(envioQueue).to(exchange).with(QUEUE_ENVIO);
    }



    //RabbitTemplate para usar o conversor JSON
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }


    // -----------------------------################----------------------------------
    //Fila de RECEBIMENTO DA SOLICITAsAO DE MATERIAIS DO SISTEMA DE CONSULTAS
    // -----------------------------################----------------------------------

    // NOVAS CHAVES (Entrada para Estoque)
    public static final String EXCHANGE_ESTOQUE = "appointments.estoque";
    public static final String ROUTING_KEY_ESTOQUE = "appointments.confirmed.estoque";
    public static final String QUEUE_ESTOQUE = "estoque.resource.input"; // Nova Fila de Recebimento

    //NOVA Fila de Recebimento
    @Bean
    public Queue estoqueQueue() {
        return new Queue(QUEUE_ESTOQUE, true); // true = fila persistente
    }

    //NOVO Exchange (usando TopicExchange, que é mais flexível para chaves diversas)
    @Bean
    public Exchange estoqueExchange() {
        return new TopicExchange(EXCHANGE_ESTOQUE);
    }

    //Liga a NOVA Fila de Recebimento ao NOVO Exchange
    @Bean
    public Binding estoqueBinding(Queue estoqueQueue, TopicExchange estoqueExchange) {
        // A fila é ligada usando a Routing Key completa
        return BindingBuilder.bind(estoqueQueue).to(estoqueExchange).with(ROUTING_KEY_ESTOQUE);
    }

    // -----------------------------################----------------------------------


}
