package com.heladix.domain.product;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductIdTest {

    @Test
    void shouldCreateProductId() {

        ProductId productId = ProductId.create();

        assertNotNull(productId);
        assertNotNull(productId.value());
    }

    @Test
    void shouldCreateDifferentIds() {

        ProductId first = ProductId.create();
        ProductId second = ProductId.create();

        assertNotEquals(first, second);
    }

    @Test
    void shouldCreateProductIdFromExistingUuid() {

        UUID uuid = UUID.randomUUID();

        ProductId productId = ProductId.from(uuid);

        assertEquals(uuid, productId.value());
    }

    @Test
    void shouldNotAllowNullValue() {

        assertThrows(
                NullPointerException.class,
                () -> new ProductId(null)
        );
    }
}
