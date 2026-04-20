MENU-SERVICE

Ordre de lancement :
1. Eureka Server
2. Config Server
3. API Gateway
4. menu-service

Endpoints :
- GET    http://localhost:8083/menus
- GET    http://localhost:8083/menus/1
- GET    http://localhost:8083/menus/available
- GET    http://localhost:8083/menus/category/PIZZA
- POST   http://localhost:8083/menus
- DELETE http://localhost:8083/menus/1

Via Gateway :
- GET    http://localhost:8082/menus
- GET    http://localhost:8082/menus/1
- GET    http://localhost:8082/menus/available

Exemple JSON POST :
{
  "name": "Tacos Mixte",
  "description": "Tacos avec viande et fromage",
  "price": 19.5,
  "category": "FAST_FOOD",
  "available": true
}

Remarque :
- Le service charge 3 menus de test au démarrage.
- Le port du gateway dans ce README est 8082.
