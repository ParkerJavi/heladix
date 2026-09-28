package com.heladix.infrastructure.persistence.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    private UUID id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "sku", nullable = false, length = 50, unique = true)
    private String sku;

    @Column(name = "type", nullable = false, length = 30)
    private String type;

    @Column(name = "inventory_unit", nullable = false, length = 20)
    private String inventoryUnit;

    @Column(name = "cost_amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal costAmount;

    @Column(name = "cost_currency", nullable = false, length = 3)
    private String costCurrency;

    @Column(name = "selling_price_amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal sellingPriceAmount;

    @Column(name = "selling_price_currency", nullable = false, length = 3)
    private String sellingPriceCurrency;

    @Column(name = "active", nullable = false)
    private boolean active;

    protected ProductEntity() {
        // Constructor requerido por JPA
    }

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