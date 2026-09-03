package com.heladix.infrastructure.persistence.product;

import com.heladix.domain.product.Product;

public interface ProductPersistencePort {

    void save(Product product);
}
