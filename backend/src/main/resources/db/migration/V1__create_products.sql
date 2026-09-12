CREATE TABLE products (
    id UUID PRIMARY KEY,

    name VARCHAR(150) NOT NULL,

    description TEXT,

    sku VARCHAR(50) NOT NULL UNIQUE,

    type VARCHAR(30) NOT NULL,

    inventory_unit VARCHAR(20) NOT NULL,

    cost_amount NUMERIC(12,2) NOT NULL,

    cost_currency VARCHAR(3) NOT NULL,

    selling_price_amount NUMERIC(12,2) NOT NULL,

    selling_price_currency VARCHAR(3) NOT NULL,

    active BOOLEAN NOT NULL
);
