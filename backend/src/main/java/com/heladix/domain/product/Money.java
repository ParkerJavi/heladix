package com.heladix.domain.product;

import java.math.BigDecimal;
import java.util.Objects;

public record Money(BigDecimal amount, String currency) {

    public Money {
        Objects.requireNonNull(amount, "Money amount cannot be null");
        Objects.requireNonNull(currency, "Money currency cannot be null");

        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Money amount cannot be negative");
        }

        if (currency.isBlank()) {
            throw new IllegalArgumentException("Money currency cannot be blank");
        }
    }
}
