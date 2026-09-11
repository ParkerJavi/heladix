package com.heladix.infrastructure.persistence.product;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductEntityTest {

    @Test
    void shouldCreateProductEntity() {

        UUID id = UUID.randomUUID();

        ProductEntity entity = new ProductEntity(
                id,
                "Helado de vainilla",
                "Helado sabor vainilla",
                "HEL-VAN-001",
                "ICE_CREAM",
                "LITER",
                new BigDecimal("35.00"),
                "MXN",
                new BigDecimal("60.00"),
                "MXN",
                true
        );

        assertEquals(id, entity.getId());
        assertEquals("Helado de vainilla", entity.getName());
        assertEquals("Helado sabor vainilla", entity.getDescription());
        assertEquals("HEL-VAN-001", entity.getSku());
        assertEquals("ICE_CREAM", entity.getType());
        assertEquals("LITER", entity.getInventoryUnit());
        assertEquals(new BigDecimal("35.00"), entity.getCostAmount());
        assertEquals("MXN", entity.getCostCurrency());
        assertEquals(new BigDecimal("60.00"), entity.getSellingPriceAmount());
        assertEquals("MXN", entity.getSellingPriceCurrency());
        assertEquals(true, entity.isActive());
    }
}