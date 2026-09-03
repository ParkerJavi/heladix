package com.heladix.infrastructure.persistence.product;

import com.heladix.application.product.ProductRepository;
import com.heladix.domain.product.Product;

public class ProductRepositoryImpl implements ProductRepository {

    private final ProductPersistencePort persistencePort;

    public ProductRepositoryImpl(ProductPersistencePort persistencePort) {
        this.persistencePort = persistencePort;
    }

    @Override
    public void save(Product product) {
        persistencePort.save(product);
    }
}
