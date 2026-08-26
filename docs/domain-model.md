# HELADIX Domain Model

## 1. Domain Overview

HELADIX manages products, their commercial presentations, sales and
physical inventory.

A key distinction in the domain is the difference between what is
stored in inventory and what is offered to the customer.

The system must model these concepts independently.

---

## 2. Product

A Product represents an item that physically exists and is managed
as inventory.

Examples:

- Vanilla ice cream
- Strawberry ice cream
- Chocolate ice cream
- Mango popsicle
- Chocolate cookies
- Peanuts

A Product is not necessarily the same thing that the customer sees
as a complete sale item.

### Product characteristics

A Product may have:

- Identity
- Name
- Description
- Type
- SKU
- Inventory unit
- Cost
- Active status

The inventory unit represents how the product is physically
controlled.

For ice cream, the inventory unit will be liters.

---

## 3. Product Types

Products may belong to different business types.

Initial examples include:

- ICE_CREAM
- POPSICLE
- SNACK

The product type may determine which commercial rules apply.

For example, ice cream can be offered in multiple commercial
presentations, while a popsicle is normally sold as an individual
unit.

---

## 4. Flavor

Flavor represents the flavor associated with an inventory product.

Examples:

- Vanilla
- Strawberry
- Chocolate
- Mango
- Lemon

Products such as ice cream and popsicles may have different flavors.

Not every product requires a flavor.

For example, snacks may have a single commercial flavor or no flavor
variation relevant to the HELADIX domain.

Flavor is conceptually different from Product Type.

---

## 5. Presentation

A Presentation represents how a product or group of products is
offered to the customer.

Examples:

- Small ice cream
- Medium ice cream
- Large ice cream
- Individual popsicle
- Individual snack

A Presentation defines the commercial characteristics of the sale.

For example:

- Small: 1 scoop
- Medium: 2 scoops
- Large: 3 scoops

The presentation does not determine the specific flavor selected
by the customer.

---

## 6. Sale Composition

A sale may contain a Presentation together with the inventory
products selected by the customer.

Example:

Medium ice cream:

- 1 scoop of vanilla ice cream
- 1 scoop of strawberry ice cream

Another customer may purchase the same presentation with:

- 2 scoops of chocolate ice cream

Therefore, the presentation and the actual inventory products
consumed are separate concepts.

---

## 7. Inventory

Inventory represents the physical existence of Products managed by
HELADIX.

The inventory quantity is expressed using the product's inventory
unit.

For ice cream, inventory is controlled in liters.

Example:

Vanilla ice cream:

- Inventory unit: liter
- Current quantity: 12.5 L

The system should not assume that a scoop always represents an
exact physical quantity.

---

## 8. Theoretical Consumption

Sales may generate a theoretical consumption of inventory.

For example:

A medium ice cream may represent two scoops.

The system may use an estimated consumption quantity to calculate
the expected inventory reduction.

This value represents an operational estimate rather than a
guarantee of the exact physical quantity consumed.

---

## 9. Physical Inventory Differences

The physical quantity may differ from the theoretical quantity due
to factors such as:

- Human variation in portion sizes
- Spillage
- Waste
- Product remaining in containers
- Operational mistakes

Physical differences must be recordable through inventory
adjustments or losses.

The system must not assume that every theoretical consumption will
perfectly match the physical reality.

---

## 10. Sale

A Sale represents a completed commercial transaction with a
customer.

A sale may contain one or more sale items.

A sale item may include:

- Presentation
- Quantity
- Price
- Selected inventory products
- Theoretical consumption

Example:

Sale #001

- Presentation: Medium ice cream
- Quantity: 1
- Price: $40

Composition:

- Vanilla ice cream: 1 scoop
- Strawberry ice cream: 1 scoop

---

## 11. Important Domain Distinctions

HELADIX must keep the following concepts separate:

### Product

What physically exists and is controlled in inventory.

### Presentation

How something is offered commercially to the customer.

### Sale

The commercial transaction performed by the customer.

### Inventory

The physical existence of Products.

### Theoretical Consumption

The expected amount of inventory consumed by a sale.

### Physical Adjustment

The correction required when physical inventory differs from the
theoretical inventory.

---

## 12. Initial Domain Relationships

The initial conceptual relationship is:

Product
    |
    | is stored in
    v
Inventory

Presentation
    |
    | is offered through
    v
Sale

Sale
    |
    | consumes
    v
Product

The consumption of Products may be estimated using the rules of the
Presentation.

---

## 13. Domain Evolution

This model is intentionally evolutionary.

Additional concepts such as recipes, production, batches, pricing
rules, inventory movements, losses and purchasing will be added as
their business rules are defined.

The domain model must be refined before implementation whenever a
new business concept introduces significant rules or relationships.
---

## 14. Product Identity

A Product has its own identity within the HELADIX domain.

Changes to the product's descriptive or economic information do not
create a new Product identity.

For example, changing the cost of Vanilla Ice Cream does not create
a different product.

The Product identity remains the same while its information evolves.

---

## 15. Product Cost

Product cost represents the current economic cost associated with
the inventory product.

Cost may change over time due to factors such as:

- Supplier price changes
- Raw material costs
- Inflation
- Purchasing conditions

A change in cost does not change the identity of the Product.

Historical cost information may be required in the future for
production, purchasing, inventory valuation or reporting.

The historical cost model will be defined when those business
requirements are implemented.

---

## 16. Product and Inventory Separation

Product and Inventory are separate domain concepts.

Product describes what the item is.

Inventory describes how much physical product currently exists.

Therefore, Product does not own its stock quantity.

Example:

Product:

    Vanilla Ice Cream

Inventory:

    Product: Vanilla Ice Cream
    Unit: Liter
    Quantity: 12.5 L

The inventory quantity must be managed through the Inventory domain
rather than directly through Product.

---

## 17. Inventory Invariants

Inventory must protect the consistency of physical quantities.

The domain must prevent invalid inventory states such as negative
quantities.

Inventory changes should occur through explicit domain operations
rather than unrestricted direct modification of the quantity.

Examples of future inventory operations include:

- Receive inventory
- Consume inventory
- Register loss
- Adjust inventory

The exact rules for each operation will be defined when the
Inventory domain is implemented.

---

## 18. Aggregate Candidates

The current domain model identifies the following initial aggregate
candidates:

### Product Aggregate

Product is currently considered an Aggregate Root.

Its responsibility is to protect the identity and business rules
associated with an inventory-managed product.

Product does not directly manage inventory quantities.

### Inventory Aggregate

Inventory is considered a separate Aggregate Root.

Its responsibility is to protect the physical quantity and inventory
movement rules associated with a Product.

### Sale Aggregate

Sale is considered an Aggregate Root.

Its responsibility is to protect the consistency of a commercial
transaction and its sale items.

These aggregate boundaries are provisional and may evolve as the
domain becomes more detailed.

---

## 19. Value Object Candidates

The following concepts are currently considered candidates for
Value Objects:

- ProductId
- Sku
- Money
- Quantity
- InventoryUnit

A Value Object represents a value whose identity comes from its
attributes rather than from a separate lifecycle.

These concepts will be implemented as Value Objects only after their
business rules are understood sufficiently.

---

## 20. Domain Modeling Principles

The HELADIX domain model should follow these principles:

- Business rules belong to the domain.
- Domain objects should protect their own invariants.
- Product must not manage inventory quantities.
- Inventory must protect physical quantity rules.
- Sales must protect the consistency of commercial transactions.
- Technical frameworks must not define the domain model.
- Persistence requirements must not dictate business concepts.
- Domain concepts should be named according to the language of the
  business.

The domain model should be refined whenever new business rules are
discovered.