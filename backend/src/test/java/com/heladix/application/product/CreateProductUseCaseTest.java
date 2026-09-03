package com.heladix.application.product;

import com.heladix.domain.product.InventoryUnit;
import com.heladix.domain.product.Money;
import com.heladix.domain.product.ProductType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateProductUseCaseTest {
    private ProductRepository repository;
    private CreateProductUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = mock(ProductRepository.class);
        useCase = new CreateProductUseCase(repository);
    }

    @Test
    void shouldCreateProduct() {

        Money cost = new Money(
                new BigDecimal("35.00"),
                "MXN"
        );

        Money sellingPrice = new Money(
                new BigDecimal("60.00"),
                "MXN"
        );

        var product = useCase.execute(
                "Helado de vainilla",
                "Helado sabor vainilla",
                "HEL-VAN-001",
                List.of("vainilla"),
                ProductType.ICE_CREAM,
                InventoryUnit.LITER,
                cost,
                sellingPrice
        );

        assertNotNull(product);
        assertNotNull(product.id());

        assertEquals("Helado de vainilla", product.name());
        assertEquals("Helado sabor vainilla", product.description());
        assertEquals("HEL-VAN-001", product.sku());
        assertEquals(List.of("vainilla"), product.flavors());
        assertEquals(ProductType.ICE_CREAM, product.type());
        assertEquals(InventoryUnit.LITER, product.inventoryUnit());
        assertEquals(cost, product.cost());
        assertEquals(sellingPrice, product.sellingPrice());
        assertTrue(product.active());
    }
    @Test
    void shouldNotCreateProductWhenSellingPriceIsLowerThanCost() {

        Money cost = new Money(
                new BigDecimal("60.00"),
                "MXN"
        );

        Money sellingPrice = new Money(
                new BigDecimal("50.00"),
                "MXN"
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> useCase.execute(
                        "Helado de vainilla",
                        "Helado sabor vainilla",
                        "HEL-VAN-001",
                        List.of("vainilla"),
                        ProductType.ICE_CREAM,
                        InventoryUnit.LITER,
                        cost,
                        sellingPrice
                )
        );
    }

    @Test
    void shouldNotCreateProductWithDifferentCurrencies() {

        Money cost = new Money(
                new BigDecimal("35.00"),
                "MXN"
        );

        Money sellingPrice = new Money(
                new BigDecimal("4.00"),
                "USD"
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> useCase.execute(
                        "Helado de vainilla",
                        "Helado sabor vainilla",
                        "HEL-VAN-001",
                        List.of("vainilla"),
                        ProductType.ICE_CREAM,
                        InventoryUnit.LITER,
                        cost,
                        sellingPrice
                )
        );
    }

    @Test
    void shouldNotCreateProductWithBlankName() {

        assertThrows(
                IllegalArgumentException.class,
                () -> useCase.execute(
                        "   ",
                        "Helado sabor vainilla",
                        "HEL-VAN-001",
                        List.of("vainilla"),
                        ProductType.ICE_CREAM,
                        InventoryUnit.LITER,
                        new Money(new BigDecimal("35.00"), "MXN"),
                        new Money(new BigDecimal("60.00"), "MXN")
                )
        );
    }

    @Test
    void shouldSaveCreatedProduct() {

        Money cost = new Money(
                new BigDecimal("35.00"),
                "MXN"
        );

        Money sellingPrice = new Money(
                new BigDecimal("60.00"),
                "MXN"
        );

        var product = useCase.execute(
                "Helado de vainilla",
                "Helado sabor vainilla",
                "HEL-VAN-001",
                List.of("vainilla"),
                ProductType.ICE_CREAM,
                InventoryUnit.LITER,
                cost,
                sellingPrice
        );

        verify(repository).save(product);
    }
}
