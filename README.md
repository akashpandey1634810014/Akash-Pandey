# Warehouse Books/Notebooks + Offline Payment System (Spring Boot Microservices)

This project provides a microservices-based system for:
- **Warehouse management** of **Books** and **Notebooks** stock.
- **Offline payment software** for laptop usage.

## Services

1. **inventory-service** (`:8081`)
   - Create/list products
   - Product types: `BOOK`, `NOTEBOOK`
   - Update stock by selling quantity

2. **payment-service** (`:8082`)
   - Record payment with mode `OFFLINE`
   - List payment history
   - Methods: `CASH`, `CARD`, `UPI`

3. **frontend-service** (`:8080`)
   - Browser UI for inventory and payment actions

## Run locally (3 terminals)

```bash
cd inventory-service && mvn spring-boot:run
cd payment-service && mvn spring-boot:run
cd frontend-service && mvn spring-boot:run
```

Open: `http://localhost:8080`

## Build all modules

```bash
mvn clean package
```

## API Examples

### Add product
```bash
curl -X POST http://localhost:8081/api/inventory/products \
  -H "Content-Type: application/json" \
  -d '{"name":"Spring in Action","type":"BOOK","quantity":50,"unitPrice":450}'
```

### Record offline payment
```bash
curl -X POST http://localhost:8082/api/payments \
  -H "Content-Type: application/json" \
  -d '{"invoiceNo":"INV-10","customerName":"Ravi","method":"CASH","amount":500,"mode":"OFFLINE"}'
```
