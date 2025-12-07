package br.upe.intsis.estoque.amqp;

public class OutputData_Estoque_Consulta {

    private String processedId;
    private boolean success;

    public OutputData_Estoque_Consulta(String processedId, boolean success) {
        this.processedId = processedId;
        this.success = true;
    }

    // Getters e Setters necessários para o Jackson
    public String getProcessedId() {
        return processedId;
    }

    public void setProcessedId(String processedId) {
        this.processedId = processedId;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "OutputData{" +
                "processedId='" + processedId + '\'' +
                ", success=" + success +
                '}';
    }
}
