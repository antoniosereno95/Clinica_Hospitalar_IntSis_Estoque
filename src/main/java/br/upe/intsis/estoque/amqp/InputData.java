package br.upe.intsis.estoque.amqp;

public class InputData {

    private String id;
    private String message;
    private int quantity;

    public InputData() {
    }

    public InputData(String id, String message, int quantity) {
        this.id = id;
        this.message = message;
        this.quantity = quantity;
    }

    // Getters e Setters necessários para o Jackson

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "InputData{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
