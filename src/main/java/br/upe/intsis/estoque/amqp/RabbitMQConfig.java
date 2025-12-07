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

    //RabbitTemplate para usar o conversor JSON
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }



    // -----------------------------################----------------------------------
    //Fila de RECEBIMENTO DA SOLICITACAO DE MATERIAIS DO SISTEMA DE CONSULTAS
    // -----------------------------################----------------------------------

    //CHAVES (Entrada para Estoque)
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
    public TopicExchange estoqueExchange() {
        return new TopicExchange(EXCHANGE_ESTOQUE);
    }

    //Liga a NOVA Fila de Recebimento ao NOVO Exchange
    @Bean
    public Binding estoqueBinding(Queue estoqueQueue, TopicExchange estoqueExchange) {
        // A fila é ligada usando a Routing Key completa
        return BindingBuilder.bind(estoqueQueue).to(estoqueExchange).with(ROUTING_KEY_ESTOQUE);
    }
    // -----------------------------################----------------------------------

    // -----------------------------################----------------------------------
    //Fila de Envio do Retorno DA SOLICITACAO DE MATERIAIS DO SISTEMA DE CONSULTAS
    // -----------------------------################----------------------------------

    //CHAVES (Retorno para Consultas)
    public static final String QUEUE_RETORNO = "retorno.estoque.status";

    //Fila de Retorno/Saída
    @Bean
    public Queue retornoQueue() {
        return new Queue(QUEUE_RETORNO, true);
    }

    //Exchange de Retorno (TopicExchange é um bom padrão)
    @Bean
    public Exchange retornoExchange() {
        return new TopicExchange(EXCHANGE_ESTOQUE);
    }

    //Liga a Fila de Retorno ao Exchange de Retorno
    @Bean
    public Binding retornoBinding(Queue retornoQueue, TopicExchange retornoExchange) {
        return BindingBuilder.bind(retornoQueue).to(retornoExchange).with(ROUTING_KEY_ESTOQUE);
    }
    // -----------------------------################----------------------------------


    // -----------------------------################----------------------------------
    // Fila de Envio do Financeiro
    // -----------------------------################----------------------------------

    //CHAVES (Envio para o Financeiro)
    public static final String EXCHANGE_FINANCEIRO = "resources.request.finance";
    public static final String ROUTING_KEY_FINANCEIRO = "resources.request.finance";
    //A fila que o Financeiro CUMPRE O PAPEL DE CONSUMIR
    public static final String QUEUE_FINANCEIRO_REQUEST = "financeiro.resource.request";

    //Cria a Fila de Envio (a que o sistema Financeiro irá escutar)
    @Bean
    public Queue financeiroRequestQueue() {
        return new Queue(QUEUE_FINANCEIRO_REQUEST, true);
    }

    //Cria o Exchange de Envio
    @Bean
    public Exchange financeiroExchange() {
        return new TopicExchange(EXCHANGE_FINANCEIRO);
    }

    //Liga a Fila de Envio ao Exchange
    @Bean
    public Binding financeiroBinding(Queue financeiroRequestQueue, TopicExchange financeiroExchange) {
        return BindingBuilder.bind(financeiroRequestQueue).to(financeiroExchange).with(ROUTING_KEY_FINANCEIRO);
    }
    // -----------------------------################----------------------------------


}
