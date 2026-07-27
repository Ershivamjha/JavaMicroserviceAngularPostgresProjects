# Commerce Microservices

A runnable Spring Boot microservices example with independently deployable Catalog and Order services behind a Spring Cloud Gateway. Each service owns its database; the order service calls the catalog service only to validate a SKU and retrieve its price.

## Architecture

```text
Client → API Gateway (:8080) → Catalog service (:8081) → catalog-db
                            → Order service   (:8082) → orders-db
                                      │
                                      └──────────→ Catalog service
```

## Run

`docker compose up --build`

Use `http://localhost:8080/catalog/api/products` and `http://localhost:8080/orders/api/orders`. The gateway routes `/catalog/**` and `/orders/**`; the services are also exposed locally for debugging.

### Example

```bash
curl http://localhost:8080/catalog/api/products
curl -X POST http://localhost:8080/orders/api/orders -H 'content-type: application/json' -d '{"customerEmail":"buyer@example.com","sku":"LAPTOP-01","quantity":2}'
```

## Production evolution

Add OAuth2 at the gateway, service discovery/config management, Resilience4j timeouts/circuit breakers, Kafka with an outbox for domain events, Flyway migrations, tracing/metrics, and Testcontainers contract/integration tests.
