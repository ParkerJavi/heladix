package com.heladix.domain.product;

import java.util.Objects;
import java.util.UUID;

public record ProductId(UUID value) {

    public ProductId {
        Objects.requireNonNull(value, "ProductId value cannot be null");
    }

    public static ProductId create() {
        return new ProductId(UUID.randomUUID());
    }

    public static ProductId from(UUID value) {
        return new ProductId(value);
    }
}
