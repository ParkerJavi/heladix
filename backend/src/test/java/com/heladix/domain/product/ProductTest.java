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
    void shouldNotAllowChangingCostToDifferentCurrency() {

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
                new BigDecimal("8.00"),
                "USD"
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> product.changeCost(newCost)
        );
    }
    @Test
    void shouldRemainInactiveWhenDeactivatedMultipleTimes() {

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
        product.deactivate();

        assertFalse(product.active());
    }
    @Test
    void shouldRemainActiveWhenActivatedMultipleTimes() {

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

        product.activate();
        product.activate();

        assertTrue(product.active());
    }
    @Test
    void shouldNotAllowBlankFlavor() {

        assertThrows(
                IllegalArgumentException.class,
                () -> Product.create(
                        ProductId.create(),
                        "Helado de mango",
                        "",
                        "HEL-MAN-001",
                        List.of("mango", ""),
                        ProductType.ICE_CREAM,
                        InventoryUnit.LITER,
                        new Money(new BigDecimal("120.00"), "MXN")
                )
        );
    }
    @Test
    void shouldAllowBlankProductDescription() {

        Product product = Product.create(
                ProductId.create(),
                "Bolsa de cacahuates",
                "",
                "CAC-001",
                List.of(),
                ProductType.SNACK,
                InventoryUnit.UNIT,
                new Money(new BigDecimal("20.00"), "MXN")
        );

        assertNotNull(product);
        assertEquals("", product.description());
    }
    @Test
    void shouldNotAllowBlankProductSku() {

        assertThrows(
                IllegalArgumentException.class,
                () -> Product.create(
                        ProductId.create(),
                        "Bolsa de cacahuates",
                        "Bolsa de cacahuates",
                        "   ",
                        List.of(),
                        ProductType.SNACK,
                        InventoryUnit.UNIT,
                        new Money(new BigDecimal("20.00"), "MXN")
                )
        );
    }
    @Test
    void shouldNotAllowBlankProductName() {

        assertThrows(
                IllegalArgumentException.class,
                () -> Product.create(
                        ProductId.create(),
                        "   ",
                        "Producto de prueba",
                        "TEST-001",
                        List.of(),
                        ProductType.SNACK,
                        InventoryUnit.UNIT,
                        new Money(new BigDecimal("20.00"), "MXN")
                )
        );
    }
    @Test
    void shouldAllowProductWithoutFlavors() {

        Product product = Product.create(
                ProductId.create(),
                "Bolsa de cacahuates",
                "Bolsa de cacahuates",
                "CAC-001",
                List.of(),
                ProductType.SNACK,
                InventoryUnit.UNIT,
                new Money(new BigDecimal("20.00"), "MXN")
        );

        assertNotNull(product);
        assertTrue(product.flavors().isEmpty());
    }
    @Test
    void shouldNotAllowExternalModificationOfFlavors() {

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

        assertThrows(
                UnsupportedOperationException.class,
                () -> product.flavors().add("chocolate")
        );
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
