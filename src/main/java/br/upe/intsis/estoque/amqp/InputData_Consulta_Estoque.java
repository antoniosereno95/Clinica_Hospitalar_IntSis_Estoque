package br.upe.intsis.estoque.amqp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class InputData_Consulta_Estoque {

    // Campos da chave do JSON
    private String id;
    private String budgetItemId;
    private BigDecimal moneyAmount;
    private String item;
    private String category;
    private String justification;
    private Integer quantity;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //Construtor padrão (obrigatório para Jackson)
    public InputData_Consulta_Estoque() {
    }

    //Construtor completo
    public InputData_Consulta_Estoque(String id, String budgetItemId, BigDecimal moneyAmount, String item, String category, String justification, Integer quantity, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.budgetItemId = budgetItemId;
        this.moneyAmount = moneyAmount;
        this.item = item;
        this.category = category;
        this.justification = justification;
        this.quantity = quantity;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    //GETTERS and SETTERS (Obrigatórios para Jackson)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBudgetItemId() {
        return budgetItemId;
    }

    public void setBudgetItemId(String budgetItemId) {
        this.budgetItemId = budgetItemId;
    }

    public BigDecimal getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    //toString (Opcional, mas útil para o log)
    @Override
    public String toString() {
        return "InputData_Consulta_Estoque{" +
                "id='" + id + '\'' +
                ", budgetItemId='" + budgetItemId + '\'' +
                ", moneyAmount=" + moneyAmount +
                ", item='" + item + '\'' +
                ", category='" + category + '\'' +
                ", justification='" + justification + '\'' +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }


}