package br.upe.intsis.estoque.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import br.upe.intsis.estoque.service.*;

@Component
public class Consumer {

    private static final Logger log = LoggerFactory.getLogger(Consumer.class);

    @Autowired
    private Producer producer;

    @Autowired
    private EstoqueService estoque;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_ESTOQUE)
    public void receiveJson(InputData_Consulta_Estoque inputData_Consulta_Estoque) {
        log.info("Recurso recebido na fila de Estoque: {}", inputData_Consulta_Estoque.toString());

        Boolean successStatus = false;

        try {

            // ->>> LÓGICA DE VERIFICAÇÃO E ATUALIZAÇÃO DO ESTOQUE <<<-

            // *****************************************************************
            // A PESSOA QUE FOR DESENVOLVER A LÓGICA DEVE MUDAR ESTE BLOCO:
            // *****************************************************************
            // *****************************************************************

            successStatus = estoque.verificaEstoque(inputData_Consulta_Estoque);

            if (successStatus) {

                //Enviamos a confirmação para o sistema de Consulta
                successStatus = true;

                //Cria o objeto de retorno usando a variável 'successStatus'
                OutputData_Estoque_Consulta outputDataEstoqueConsulta = new OutputData_Estoque_Consulta(
                        inputData_Consulta_Estoque.getId(),
                        successStatus
                );

                //Envia a resposta de status para o sistema de consultas
                producer.sendJsonConsulta(outputDataEstoqueConsulta);


            }else{
                //Enviamos a Solicitação de Novos Materiais para o Financeiro
                successStatus = false;

                OutputData_Estoque_Financeiro outputDataEstoqueFinanceiro = new OutputData_Estoque_Financeiro(
                        inputData_Consulta_Estoque.getMoneyAmount(), inputData_Consulta_Estoque.getItem() ,
                        inputData_Consulta_Estoque.getCategory() , inputData_Consulta_Estoque.getJustification() ,
                        inputData_Consulta_Estoque.getQuantity() , inputData_Consulta_Estoque.getDescription()
                );


                //Envia a resposta de status para o sistema de consultas
                producer.sendJsonFinanceiro(outputDataEstoqueFinanceiro);
            }

        } catch (Exception e) {
            log.error("Erro ao processar estoque para ID {}: {}", inputData_Consulta_Estoque.getId(), e.getMessage());
            successStatus = false;
        }

    }


}


