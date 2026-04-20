ORDER-SERVICE

Ordre de lancement :
1. Eureka Server
2. Config Server
3. API Gateway
4. order-service

Port :
- service : 8086

Endpoints directs :
- GET    http://localhost:8086/orders
- GET    http://localhost:8086/orders/1
- GET    http://localhost:8086/orders/status/PENDING
- GET    http://localhost:8086/orders/customer/Haneene
- POST   http://localhost:8086/orders
- PUT    http://localhost:8086/orders/1
- DELETE http://localhost:8086/orders/1

Exemple JSON POST :
{
  "orderNumber": "ORD-100",
  "customerName": "Haneene",
  "menuName": "Tacos Mixte",
  "totalPrice": 19.5,
  "status": "PENDING"
}

Remarques :
- Base H2 en mémoire
- 3 commandes de test au démarrage
- Pour passer via le gateway, ajoute la route /orders/** dans api-gateway
