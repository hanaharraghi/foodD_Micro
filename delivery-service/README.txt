DELIVERY-SERVICE

Ordre de lancement :
1. Eureka Server
2. Config Server
3. API Gateway
4. delivery-service

Port :
- service : 8085

Endpoints directs :
- GET    http://localhost:8085/deliveries
- GET    http://localhost:8085/deliveries/1
- GET    http://localhost:8085/deliveries/status/PENDING
- POST   http://localhost:8085/deliveries
- PUT    http://localhost:8085/deliveries/1
- DELETE http://localhost:8085/deliveries/1

Exemple JSON POST :
{
  "customerName": "Haneene",
  "address": "Ariana",
  "status": "PENDING",
  "transportType": "Moto"
}

Remarques :
- Base H2 en mémoire
- 3 livraisons de test au démarrage
- Pour passer via le gateway, ajoute la route /deliveries/** dans api-gateway
