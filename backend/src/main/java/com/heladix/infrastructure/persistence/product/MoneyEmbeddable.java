package com.heladix.infrastructure.persistence.product;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

@Embeddable
public class MoneyEmbeddable {

    private BigDecimal amount;
    private String currency;

    protected MoneyEmbeddable(){

    }

    public MoneyEmbeddable(
            BigDecimal amount,
            String currency
    ) {
        this.amount = amount;
        this.currency = currency;
    }


    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
}