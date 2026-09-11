package com.heladix.infrastructure.persistence.product;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductEntity {

    private UUID id;
    private String name;
    private String description;
    private String sku;
    private String type;
    private String inventoryUnit;
    private BigDecimal costAmount;
    private String costCurrency;
    private BigDecimal sellingPriceAmount;
    private String sellingPriceCurrency;
    private boolean active;

    public ProductEntity(
            UUID id,
            String name,
            String description,
            String sku,
            String type,
            String inventoryUnit,
            BigDecimal costAmount,
            String costCurrency,
            BigDecimal sellingPriceAmount,
            String sellingPriceCurrency,
            boolean active
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.sku = sku;
        this.type = type;
        this.inventoryUnit = inventoryUnit;
        this.costAmount = costAmount;
        this.costCurrency = costCurrency;
        this.sellingPriceAmount = sellingPriceAmount;
        this.sellingPriceCurrency = sellingPriceCurrency;
        this.active = active;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSku() {
        return sku;
    }

    public String getType() {
        return type;
    }

    public String getInventoryUnit() {
        return inventoryUnit;
    }

    public BigDecimal getCostAmount() {
        return costAmount;
    }

    public String getCostCurrency() {
        return costCurrency;
    }

    public BigDecimal getSellingPriceAmount() {
        return sellingPriceAmount;
    }

    public String getSellingPriceCurrency() {
        return sellingPriceCurrency;
    }

    public boolean isActive() {
        return active;
    }
}