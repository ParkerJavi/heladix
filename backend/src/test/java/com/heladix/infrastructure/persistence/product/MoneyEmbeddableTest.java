package com.heladix.infrastructure.persistence.product;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyEmbeddableTest {

    @Test
    void shouldCreateMoneyEmbeddable() {

        MoneyEmbeddable money = new MoneyEmbeddable(
                new BigDecimal("35.00"),
                "MXN"
        );

        assertEquals(
                new BigDecimal("35.00"),
                money.getAmount()
        );

        assertEquals(
                "MXN",
                money.getCurrency()
        );
    }
}