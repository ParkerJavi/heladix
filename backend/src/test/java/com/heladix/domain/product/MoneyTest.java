package com.heladix.domain.product;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    @Test
    void shouldCreateMoneyWithValidData() {

        Money money = new Money(
                new BigDecimal("120.00"),
                "MXN"
        );

        assertNotNull(money);
        assertEquals(new BigDecimal("120.00"), money.amount());
        assertEquals("MXN", money.currency());
    }

    @Test
    void shouldNotAllowNullAmount() {

        assertThrows(
                NullPointerException.class,
                () -> new Money(null, "MXN")
        );
    }

    @Test
    void shouldNotAllowNegativeAmount() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Money(
                        new BigDecimal("-1.00"),
                        "MXN"
                )
        );
    }

    @Test
    void shouldNotAllowNullCurrency() {

        assertThrows(
                NullPointerException.class,
                () -> new Money(
                        new BigDecimal("100.00"),
                        null
                )
        );
    }

    @Test
    void shouldNotAllowBlankCurrency() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Money(
                        new BigDecimal("100.00"),
                        ""
                )
        );
    }
}