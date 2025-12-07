package br.upe.intsis.estoque.amqp;

public class OutputData {

    private String processedId;
    private String statusMessage;
    private boolean success;

    public OutputData() {
    }

    public OutputData(String processedId, String statusMessage) {
        this.processedId = processedId;
        this.statusMessage = statusMessage;
        this.success = true;
    }

    // Getters e Setters necessários para o Jackson

    public String getProcessedId() {
        return processedId;
    }

    public void setProcessedId(String processedId) {
        this.processedId = processedId;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
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
                ", statusMessage='" + statusMessage + '\'' +
                ", success=" + success +
                '}';
    }
}
