package br.upe.intsis.estoque.amqp;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    //Nomes das Filas
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

    //iga a Fila de Envio ao Exchange
    @Bean
    public Binding envioBinding(Queue envioQueue, DirectExchange exchange) {
        return BindingBuilder.bind(envioQueue).to(exchange).with(QUEUE_ENVIO);
    }

    //Conversor de mensagens JSON (Jackson)
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    //RabbitTemplate para usar o conversor JSON
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}