package br.upe.intsis.estoque.amqp;

import java.math.BigDecimal;

public class OutputData_Estoque_Financeiro {

    private BigDecimal moneyAmount;
    private String item;
    private CategoriaProduto category;
    private String justification;
    private Integer quantity;
    private String description;

    public OutputData_Estoque_Financeiro() {
    }

    public OutputData_Estoque_Financeiro(
            BigDecimal moneyAmount,
            String item,
            CategoriaProduto category,
            String justification,
            Integer quantity,
            String description
    ) {
        this.moneyAmount = moneyAmount;
        this.item = item;
        this.category = category;
        this.justification = justification;
        this.quantity = quantity;
        this.description = description;
    }

    // 4. Getters e Setters (Necessários para o Jackson)
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

    public CategoriaProduto getCategory() {
        return category;
    }

    public void setCategory(CategoriaProduto category) {
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

    // 5. toString (Útil para logs)
    @Override
    public String toString() {
        return "OutputData_Estoque_Financeiro{" +
                "moneyAmount=" + moneyAmount +
                ", item='" + item + '\'' +
                ", category=" + category +
                ", quantity=" + quantity +
                '}';
    }
}


