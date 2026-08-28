package com.heladix.domain.product;

import java.util.List;
import java.util.Objects;

public class Product {

    private final ProductId id;
    private final String name;
    private final String description;
    private final String sku;
    private final ProductType type;
    private final InventoryUnit inventoryUnit;
    private Money cost;
    private boolean active;
    private final List<String> flavors;


    private Product(
            ProductId id,
            String name,
            String description,
            String sku,
            List<String> flavors,
            ProductType type,
            InventoryUnit inventoryUnit,
            Money cost

    ) {
        this.id = Objects.requireNonNull(id, "Product id cannot be null");
        this.name = Objects.requireNonNull(name, "Product name cannot be null");
        this.description = description;
        this.sku = Objects.requireNonNull(sku, "Product sku cannot be null");
        this.type = Objects.requireNonNull(type, "Product type cannot be null");
        this.inventoryUnit = Objects.requireNonNull(
                inventoryUnit,
                "Product inventory unit cannot be null"
        );
        this.flavors = List.copyOf(
                Objects.requireNonNull(flavors, "Product flavors cannot be null")
        );
        this.cost = Objects.requireNonNull(cost, "Product cost cannot be null");
        this.active = true;
    }

    public static Product create(
            ProductId id,
            String name,
            String description,
            String sku,
            List<String>flavors,
            ProductType type,
            InventoryUnit inventoryUnit,
            Money cost
    ) {
        return new Product(
                id,
                name,
                description,
                sku,
                flavors,
                type,
                inventoryUnit,
                cost
        );
    }

    public void deactivate() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }

    public ProductId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String description() {
        return description;
    }

    public String sku() {
        return sku;
    }
    public List<String> flavors() {
        return flavors;
    }

    public ProductType type() {
        return type;
    }

    public InventoryUnit inventoryUnit() {
        return inventoryUnit;
    }

    public Money cost() {
        return cost;
    }
    public void changeCost(Money newCost) {
        this.cost = Objects.requireNonNull(
                newCost,
                "Product cost cannot be null"
        );
    }

    public boolean active() {
        return active;
    }
}