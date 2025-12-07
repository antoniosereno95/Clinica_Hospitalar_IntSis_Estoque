package br.upe.intsis.estoque.amqp;

public class OutputData_Estoque_Financeiro {

    private String processedId;
    private boolean success;

    public OutputData_Estoque_Financeiro() {
    }

    public OutputData_Estoque_Financeiro(String processedId, boolean success) {
        this.processedId = processedId;
        this.success = true;
    }

    // Getters e Setters necessários para o Jackson


    //ToString


}
