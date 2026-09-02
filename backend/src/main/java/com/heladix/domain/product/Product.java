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
    private Money sellingPrice;
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
            Money cost,
            Money sellingPrice

    ) {
        this.id = Objects.requireNonNull(id, "Product id cannot be null");
        this.name = Objects.requireNonNull(name, "Product name cannot be null");
        if (name.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be blank");
        }
        this.description = description;
        this.sku = Objects.requireNonNull(sku, "Product sku cannot be null");
        if (sku.isBlank()) {
            throw new IllegalArgumentException("Product sku cannot be blank");
        }
        this.type = Objects.requireNonNull(type, "Product type cannot be null");
        this.inventoryUnit = Objects.requireNonNull(
                inventoryUnit,
                "Product inventory unit cannot be null"
        );
        List<String> validatedFlavors = Objects.requireNonNull(
                flavors,
                "Product flavors cannot be null"
        );

        if (validatedFlavors.stream().anyMatch(flavor -> flavor.isBlank())) {
            throw new IllegalArgumentException(
                    "Product flavors cannot contain blank values"
            );
        }

        this.flavors = List.copyOf(validatedFlavors);

        this.cost = Objects.requireNonNull(
                cost,
                "Product cost cannot be null"
        );

        this.sellingPrice = Objects.requireNonNull(
                sellingPrice,
                "Product selling price cannot be null"
        );

        if (!this.cost.currency().equals(this.sellingPrice.currency())) {
            throw new IllegalArgumentException(
                    "Product cost and selling price currencies must match"
            );
        }

        if (this.sellingPrice.amount().compareTo(this.cost.amount()) < 0) {
            throw new IllegalArgumentException(
                    "Product selling price cannot be lower than cost"
            );
        }

        this.active = true;
    }

    public static Product create(
            ProductId id,
            String name,
            String description,
            String sku,
            List<String> flavors,
            ProductType type,
            InventoryUnit inventoryUnit,
            Money cost,
            Money sellingPrice
    ) {
        return new Product(
                id,
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
        if (!this.active) {
            throw new IllegalStateException(
                    "Cannot change product cost when product is inactive"
            );
        }

        Objects.requireNonNull(
                newCost,
                "Product cost cannot be null"
        );

        if (!this.cost.currency().equals(newCost.currency())) {
            throw new IllegalArgumentException(
                    "Product cost currency cannot be changed"
            );
        }
        if (newCost.amount().compareTo(this.sellingPrice.amount()) > 0) {
            throw new IllegalArgumentException(
                    "Product cost cannot be greater than selling price"
            );
        }

        this.cost = newCost;
    }
    public Money sellingPrice() {
        return sellingPrice;
    }
    public void changeSellingPrice(Money newSellingPrice) {
        if (!this.active) {
            throw new IllegalStateException(
                    "Cannot change product selling price when product is inactive"
            );
        }

        Objects.requireNonNull(
                newSellingPrice,
                "Product selling price cannot be null"
        );

        if (!this.sellingPrice.currency().equals(newSellingPrice.currency())) {
            throw new IllegalArgumentException(
                    "Product selling price currency cannot be changed"
            );
        }
        if (newSellingPrice.amount().compareTo(this.cost.amount()) < 0) {
            throw new IllegalArgumentException(
                    "Product selling price cannot be lower than cost"
            );
        }

        this.sellingPrice = newSellingPrice;
    }

    public boolean active() {
        return active;
    }
}