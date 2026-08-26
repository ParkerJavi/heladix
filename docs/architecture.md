# HELADIX Architecture

## 1. Architectural Style

HELADIX uses a modular architecture inspired by Clean Architecture
and Hexagonal Architecture.

The architecture is designed to keep business rules independent
from technical details such as frameworks, databases, transport
mechanisms and external services.

---

## 2. Architectural Goals

The architecture aims to:

- Protect the business domain from technical details.
- Keep modules focused on a clear responsibility.
- Make business rules easy to test.
- Allow infrastructure details to change with minimal impact.
- Support future growth of the system without unnecessary complexity.
- Make architectural rules explicit and enforceable.

---

## 3. Dependency Rule

Dependencies should point toward the business core.

The domain must not depend on:

- Spring Framework
- PostgreSQL
- JPA/Hibernate
- HTTP
- Docker
- External services

Outer modules may depend on inner modules, but the business core
must remain independent from external technical details.

---

## 4. Main Modules

### Domain

Contains the business concepts and rules of HELADIX.

Examples include:

- Products
- Flavors
- Presentations
- Recipes
- Batches
- Inventory
- Sales

The domain represents what the business is and what rules must
always be respected.

---

### Application

Contains the use cases of the system.

Examples include:

- Register a sale
- Create a product
- Register production
- Adjust inventory
- Register a loss

Application coordinates operations while keeping technical
implementation details outside the business workflow.

---

### Infrastructure

Contains technical implementations and external concerns.

Examples include:

- PostgreSQL
- JPA/Hibernate
- Flyway
- Docker
- Repository implementations
- External service integrations

Infrastructure provides the technical mechanisms required by the
application.

---

### Presentation

Contains the mechanisms through which external clients interact
with HELADIX.

Initially this will primarily contain:

- REST controllers
- HTTP-related concerns
- Request/response DTOs

Presentation should delegate business operations to application
use cases instead of implementing business rules itself.

---

## 5. Ports and Adapters

HELADIX will use the Ports and Adapters concept to isolate the
application and domain from external technologies.

Ports define contracts required or exposed by the application.

Adapters provide concrete implementations for those contracts.

For example:

Application
    |
    v
Repository Port
    ^
    |
PostgreSQL/JPA Adapter

This allows infrastructure technologies to be replaced without
changing the core business rules unnecessarily.

---

## 6. Business Rules

Business rules belong to the domain and must not depend on how
the system is technically implemented.

Examples already defined for HELADIX include:

- Inventory cannot become negative.
- Physical inventory differences must be recorded.
- Inventory losses must be registered as losses.
- Older batches should be consumed before newer batches.
- Product pricing depends on the applicable product category
  and presentation rules.
- Product flavor does not necessarily determine its price.

These rules will evolve as the domain model becomes more detailed.

---

## 7. Testing Strategy

The architecture should allow different types of tests to be used
according to the responsibility being tested.

Examples:

- Domain tests for business rules.
- Application tests for use cases.
- Integration tests for infrastructure.
- Web tests for REST endpoints.
- Architecture tests for dependency rules.

The goal is to verify both system behavior and architectural
constraints.

---

## 8. Architectural Evolution

This document is a living document.

Architectural decisions may evolve as HELADIX grows, but changes
should be intentional and documented when they significantly
affect the structure or principles of the system.