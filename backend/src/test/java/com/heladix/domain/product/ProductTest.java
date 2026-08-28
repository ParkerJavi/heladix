package com.heladix.domain.product;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void shouldCreateProductWithValidData() {

        ProductId id = ProductId.create();

        Money cost = new Money(
                new BigDecimal("120.00"),
                "MXN"
        );

        Product product = Product.create(
                id,
                "Helado de vainilla",
                "Helado sabor vainilla",
                "HEL-VAN-001",
                List.of("vainilla"),
                ProductType.ICE_CREAM,
                InventoryUnit.LITER,
                cost
        );

        assertNotNull(product);
        assertEquals(id, product.id());
        assertEquals("Helado de vainilla", product.name());
        assertEquals(List.of("vainilla"), product.flavors());
        assertEquals(ProductType.ICE_CREAM, product.type());
        assertEquals(InventoryUnit.LITER, product.inventoryUnit());
        assertEquals(cost, product.cost());
        assertTrue(product.active());
    }
    @Test
    void shouldChangeProductCost() {

        Product product = Product.create(
                ProductId.create(),
                "Helado de vainilla",
                "Helado sabor vainilla",
                "HEL-VAN-001",
                List.of("vainilla"),
                ProductType.ICE_CREAM,
                InventoryUnit.LITER,
                new Money(new BigDecimal("120.00"), "MXN")
        );

        Money newCost = new Money(
                new BigDecimal("135.00"),
                "MXN"
        );

        product.changeCost(newCost);

        assertEquals(newCost, product.cost());
    }
    @Test
    void shouldDeactivateProduct() {

        Product product = Product.create(
                ProductId.create(),
                "Paleta de mango",
                "Paleta sabor mango",
                "PAL-MAN-001",
                List.of("mango"),
                ProductType.POPSICLE,
                InventoryUnit.UNIT,
                new Money(new BigDecimal("15.00"), "MXN")
        );

        product.deactivate();

        assertFalse(product.active());
    }
    @Test
    void shouldActivateProduct() {

        Product product = Product.create(
                ProductId.create(),
                "Paleta de mango",
                "Paleta sabor mango",
                "PAL-MAN-001",
                List.of("mango"),
                ProductType.POPSICLE,
                InventoryUnit.UNIT,
                new Money(new BigDecimal("15.00"), "MXN")
        );

        product.deactivate();
        product.activate();

        assertTrue(product.active());
    }
    @Test
    void shouldNotAllowNullCost() {

        assertThrows(
                NullPointerException.class,
                () -> Product.create(
                        ProductId.create(),
                        "Helado de vainilla",
                        "Helado sabor vainilla",
                        "HEL-VAN-001",
                        List.of("vainilla"),
                        ProductType.ICE_CREAM,
                        InventoryUnit.LITER,
                        null
                )
        );
    }
}
