package com.heladix.infrastructure.persistence.product;

import com.heladix.domain.product.InventoryUnit;
import com.heladix.domain.product.Money;
import com.heladix.domain.product.Product;
import com.heladix.domain.product.ProductId;
import com.heladix.domain.product.ProductType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ProductRepositoryImplTest {

    private ProductPersistencePort persistencePort;
    private ProductRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        persistencePort = mock(ProductPersistencePort.class);
        repository = new ProductRepositoryImpl(persistencePort);
    }

    @Test
    void shouldPersistProduct() {

        Product product = Product.create(
                ProductId.create(),
                "Helado de vainilla",
                "Helado sabor vainilla",
                "HEL-VAN-001",
                List.of("vainilla"),
                ProductType.ICE_CREAM,
                InventoryUnit.LITER,
                new Money(
                        new BigDecimal("35.00"),
                        "MXN"
                ),
                new Money(
                        new BigDecimal("60.00"),
                        "MXN"
                )
        );

        repository.save(product);

        verify(persistencePort).save(product);
    }
}
