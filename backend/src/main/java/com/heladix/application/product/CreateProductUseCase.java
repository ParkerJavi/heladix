package com.heladix.application.product;

import com.heladix.domain.product.InventoryUnit;
import com.heladix.domain.product.Money;
import com.heladix.domain.product.Product;
import com.heladix.domain.product.ProductId;
import com.heladix.domain.product.ProductType;

import java.util.List;

public class CreateProductUseCase {

    public Product execute(
            String name,
            String description,
            String sku,
            List<String> flavors,
            ProductType type,
            InventoryUnit inventoryUnit,
            Money cost,
            Money sellingPrice
    ) {

        return Product.create(
                ProductId.create(),
                name,
                description,
                sku,
                flavors,
                type,
                inventoryUnit,
                cost,
                sellingPrice
        );
    }
}